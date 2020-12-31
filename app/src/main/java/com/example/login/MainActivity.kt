package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }*/
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()

        //val signup = findViewById<Button>(R.id.signup)


        signup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        /*fun open_signup(view: View) {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }*/

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?) {

    }

//    fun open_signup(view: View) {
//        startActivity(Intent(this, SignUpActivity::class.java))
//        finish()
//    }


}