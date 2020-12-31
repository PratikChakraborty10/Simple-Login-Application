package com.example.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_main.*

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        /*window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }*/
        
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()
        //auth = Firebase.auth


         //var signupBt = findViewById<Button>(R.id.sign_up_bt_sign)

        sign_up_bt_sign.setOnClickListener {
            signUpUser()
        }


    }
    //var tv_username = findViewById<EditText>(R.id.email_et)
    //var tv_pass = findViewById<EditText>(R.id.pass_et)
    private fun signUpUser() {
        if(email_et.text.toString().isEmpty()) {
            email_et.error = "Please enter email ID"
            email_et.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email_et.text.toString()).matches()) {
            email_et.error = "Please enter a valid Email ID"
            email_et.requestFocus()
            return
        }
        if(pass_et.text.toString().isEmpty()) {
            pass_et.error = "Please enter password"
            pass_et.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email_et.text.toString(), pass_et.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(baseContext, "Sign Up Failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()

                }

            }

    }
}