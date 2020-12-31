package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.email_et
import kotlinx.android.synthetic.main.activity_main.pass_et
import kotlinx.android.synthetic.main.activity_sign_up.*


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

        login_bt.setOnClickListener {
           doLogin()
        }

        /*fun open_signup(view: View) {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }*/

    }

    private fun doLogin() {
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
        auth.signInWithEmailAndPassword(email_et.text.toString(), pass_et.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
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