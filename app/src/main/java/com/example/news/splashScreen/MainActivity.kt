package com.example.news.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.news.R
import com.example.news.loginSignup.SignUp
import com.example.news.mainScreen.MainScreen
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Firebase :->
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        // Status :->
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        Handler().postDelayed({
            if (user!=null){
                startActivity(Intent(this@MainActivity, MainScreen::class.java))
                finish()
            }else{
                startActivity(Intent(this@MainActivity, MainScreen::class.java))
                finish()
            }
        },2000)
    }
}