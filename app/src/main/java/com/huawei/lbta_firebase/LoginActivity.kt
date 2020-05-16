package com.huawei.lbta_firebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){

    val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            val email = edittext_email_login.text.toString()
            val pass = edittext_pass_login.text.toString()

            Log.d(TAG,"email $email")
            Log.d(TAG,"email $pass")

            if (email.isEmpty() || pass.isEmpty()) return@setOnClickListener

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)
                .addOnSuccessListener {
                    if(it.user!!.isEmailVerified){
                        Log.d(TAG,"user logged in succesfully ${it.user!!.uid}")
                    }
                }
        }

        textview_back_to_registration_login.setOnClickListener {
            Log.d(TAG,"trying to go back to registration")
            finish()
        }
    }
}