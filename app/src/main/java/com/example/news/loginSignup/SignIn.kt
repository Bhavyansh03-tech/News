package com.example.news.loginSignup

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.news.R
import com.example.news.databinding.ActivitySignInBinding
import com.example.news.mainScreen.MainScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        supportRequestWindowFeature(1)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.statusBarColor = Color.TRANSPARENT
        setContentView(binding.root)

        // Firebase :->
        auth = FirebaseAuth.getInstance()

        // Direct To Login :->
        binding.signUpTxt.setOnClickListener {
            startActivity(Intent(this@SignIn, SignUp::class.java))
            finish()
        }

        // Saving Data :->
        binding.signInBtn.setOnClickListener {
            val emailTxt = binding.email.text.toString().trim()
            val passTxt = binding.pass.text.toString().trim()

            if (emailTxt.isEmpty() || passTxt.isEmpty()){
                if (emailTxt.isEmpty()){
                    binding.emailLayout.error = "Enter Your Email Address"
                    binding.email.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
                }
                if (passTxt.isEmpty()){
                    binding.passLayout.error = "Enter Your Password"
                    binding.pass.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
                }
                Toast.makeText(this@SignIn, "Enter valid details!", Toast.LENGTH_SHORT).show()
            }else if (!emailTxt.matches(emailPattern.toRegex())){
                binding.emailLayout.error = "Enter valid email address!"
                binding.email.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
            }else if (passTxt.length < 6){
                binding.passLayout.error = "Enter more than 6 character!"
                binding.pass.background = ResourcesCompat.getDrawable(resources, R.drawable.error_input_txt, null)
            }
            else{
                auth.signInWithEmailAndPassword(emailTxt, passTxt)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            startActivity(Intent(this@SignIn, MainScreen::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@SignIn,"Something went wrong, try again!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}