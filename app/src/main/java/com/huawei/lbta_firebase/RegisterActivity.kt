package com.huawei.lbta_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class RegisterActivity : AppCompatActivity() {

    val TAG = "RegisterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button_register.setOnClickListener {
            PerformRegister()
        }
        textview_already_have_an_account.setOnClickListener {
            Log.d(TAG,"trying to go to login page")
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun PerformRegister(){
        val name = edittext_username_resgister.text.toString()
        val email = edittext_email_register.text.toString()
        val pass = edittext_password_resgister.text.toString()

        if(email.isEmpty() || pass.isEmpty())
        {
            Toast.makeText(this,"plase make sure to enter email and password",Toast.LENGTH_LONG).show()
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener{
                if(!it.isSuccessful){
                    return@addOnCompleteListener
                }
                else{
                    Log.d(TAG,"successfully auth the user -${it.result!!.user!!.uid}")
                }
            }
            .addOnFailureListener {
                Log.d(TAG,"failiaure message is: ${it.message}")
                Toast.makeText(this,"failiaure message is: ${it.message}",Toast.LENGTH_LONG).show()
            }
    }
}
