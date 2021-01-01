package com.example.login


import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        sign_up_bt_sign.setOnClickListener {
            signUpUser()
        }

    }

    private fun signUpUser() {
        if (email_et.text.toString().isEmpty()) {
            email_et.error = "Please enter email"
            email_et.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email_et.text.toString()).matches()) {
            email_et.error = "Please enter valid email"
            email_et.requestFocus()
            return
        }

        if (pass_et.text.toString().isEmpty()) {
            pass_et.error = "Please enter password"
            pass_et.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email_et.text.toString(), pass_et.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Sign Up failed. Try again after some time.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }
}