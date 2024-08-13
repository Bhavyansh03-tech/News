package com.example.news.loginSignup

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.news.R
import com.example.news.databinding.ActivitySignUpBinding
import com.example.news.users.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.*

class SignUp : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        supportRequestWindowFeature(1)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.statusBarColor = Color.TRANSPARENT
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        closeKeyboard(binding.name)

        // Direct To Login :->
        binding.signInTxt.setOnClickListener {
            startActivity(Intent(this@SignUp, SignIn::class.java))
            finish()
        }

        // Saving Data :->
        binding.signupBtn.setOnClickListener {
            val nameTxt = binding.name.text.toString().trim()
            val emailTxt = binding.email.text.toString().trim()
            val passTxt = binding.pass.text.toString().trim()
            val conPassTxt = binding.conPass.text.toString().trim()

            if (nameTxt.isEmpty() || emailTxt.isEmpty() || passTxt.isEmpty() || conPassTxt.isEmpty()){
                if (nameTxt.isEmpty()){
                    binding.nameLayout.error = "Enter Your First Name"
                    binding.name.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
                }
                if (emailTxt.isEmpty()){
                    binding.emailLayout.error = "Enter Your Email Address"
                    binding.email.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
                }
                if (passTxt.isEmpty()){
                    binding.passLayout.error = "Enter Your Password"
                    binding.pass.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
                }
                if (conPassTxt.isEmpty()){
                    binding.conPassLayout.error = "Enter Your Confirm Password"
                    binding.conPass.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
                }
                Toast.makeText(this@SignUp, "Enter valid details!", Toast.LENGTH_SHORT).show()
            }else if (!emailTxt.matches(emailPattern.toRegex())){
                binding.emailLayout.error = "Enter valid email address!"
                binding.email.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
            }else if (passTxt.length < 6){
                binding.passLayout.error = "Enter more than 6 character!"
                binding.pass.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
            }else if (passTxt != conPassTxt){
                binding.conPassLayout.error = "Password not matched, try again!"
                binding.pass.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
                binding.conPass.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
            }
            else{
                auth.createUserWithEmailAndPassword(emailTxt, passTxt)
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful){
                            // Date And Time :->
                            val calender = Calendar.getInstance().time
                            val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT).format(calender)
                            val timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT).format(calender)
                            val uid = auth.currentUser?.uid
                            val user = Users(nameTxt, emailTxt, passTxt, uid, dateFormat, timeFormat)
                            database = FirebaseDatabase.getInstance().getReference("Users")
                            database.child(nameTxt).setValue(user).addOnCompleteListener {
                                startActivity(Intent(this@SignUp, SignIn::class.java))
                                finish()
                            }
                        }
                    }
            }
        }
    }

    private fun closeKeyboard(view: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}