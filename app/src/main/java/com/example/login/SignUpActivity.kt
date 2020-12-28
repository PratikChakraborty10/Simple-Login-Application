package com.example.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        val signupBt = findViewById<Button>(R.id.sign_up_bt)

        signupBt.setOnClickListener {
            signUpUser()
        }
    }
    val tv_username = findViewById<EditText>(R.id.email_et)
    val tv_pass = findViewById<EditText>(R.id.pass_et)
    private fun signUpUser() {
        if(tv_username.text.toString().isEmpty()) {
            tv_username.error = "Please enter email ID"
            tv_username.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(tv_username.text.toString()).matches()) {
            tv_username.error = "Please enter a valid Emain ID"
            tv_username.requestFocus()
            return
        }
        if(tv_pass.text.toString().isEmpty()) {
            tv_pass.error = "Please enter password"
            tv_pass.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(tv_username.text.toString(), tv_pass.text.toString())
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