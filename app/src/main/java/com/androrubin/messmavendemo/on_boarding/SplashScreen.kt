package com.androrubin.messmavendemo.on_boarding

import com.androrubin.messmavendemo.MainActivity
import com.androrubin.messmavendemo.databinding.ActivitySplashScreenBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        if (user != null) {

            Handler().postDelayed({

                val user = mAuth.currentUser
                val name = user?.uid

                db = FirebaseFirestore.getInstance()
                db.collection("Users").document("$name")
                    .get()
                    .addOnSuccessListener {

                        //Returns value of corresponding field
                        val b = it["ProfileCreated"].toString()


                        if (b=="1") {


                            val dashboardIntent = Intent(this, MainActivity::class.java)
                            startActivity(dashboardIntent)
                            finish()

                        }
                        else {

                            val dashboardIntent = Intent(this,CreateProfile::class.java)
                            startActivity(dashboardIntent)
                            finish()
                        }
                    }
            }, 1500)
        }else {

            Handler().postDelayed({

                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()

            }, 1800)

        }
    }
}