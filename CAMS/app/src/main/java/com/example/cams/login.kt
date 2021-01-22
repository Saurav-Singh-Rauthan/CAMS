package com.example.cams

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class login : AppCompatActivity() {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth = FirebaseAuth.getInstance();
    }

    fun loginToFirebase(email: String, password: String){
        mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = mAuth!!.currentUser
                        Toast.makeText(this, "User signed in sucessfully", Toast.LENGTH_SHORT).show()
                        var intent=Intent(this,MainActivity::class.java)
                        intent.putExtra("email",user!!.email)
                        intent.putExtra("uid",user!!.uid)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Error!!! user not signed in", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, "Check you're connected to internet...", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    fun loginFb(view: View) {
        loginToFirebase(etEmail.text.toString(), etPassword.text.toString())
    }
}