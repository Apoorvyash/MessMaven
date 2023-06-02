package com.androrubin.messmavendemo.on_boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.androrubin.messmavendemo.MainActivity
import com.androrubin.messmavendemo.R
import com.androrubin.messmavendemo.databinding.ActivityCreateProfileBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreateProfile : AppCompatActivity() {

    private lateinit var binding: ActivityCreateProfileBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCreateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        db= FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        val uid = currentUser?.uid
        val username = currentUser?.displayName

        val hostel_set= resources.getStringArray(R.array.hostel)
        val arrayAdapterBranch = ArrayAdapter(this,R.layout.item_branch,hostel_set)
        binding.autoCompleteHostel.setAdapter(arrayAdapterBranch)

        val name=findViewById<TextInputEditText>(R.id.name_text)
        val roll =findViewById<TextInputEditText>(R.id.roll_text)
        val hostel=findViewById<AutoCompleteTextView>(R.id.autoCompleteHostel)
        val roomNo=findViewById<TextInputEditText>(R.id.roomno_text)


        name.setText(username.toString())

        name?.doOnTextChanged { text, start, before, count ->
            if(text!!.length > 30){
                name.error ="Just enter your first and last name "
            } else if(text.length<30){
                name.error = null
            }
        }
        roll?.doOnTextChanged { text, start, before, count ->
            if(text!!.length > 30){
                roll.error ="Too long roll no. "
            } else if(text.length<30){
                roll.error = null
            }
        }

        roomNo?.doOnTextChanged { text, start, before, count ->
            if(text!!.length !=3){
                roomNo.error ="Invalid room no."
            }
        }

        binding.btnSignup.setOnClickListener {

            if (TextUtils.isEmpty(name.getText()?.trim().toString())) {
                name.error = "Field cannot be empty"
                name.requestFocus()
            }else  if (TextUtils.isEmpty(roll.getText()?.trim().toString())) {
                roll.error = "Field cannot be empty"
                roll.requestFocus()
            }
            else  if (TextUtils.isEmpty(roomNo.getText()?.trim().toString())) {
                roomNo.error = "Field cannot be empty"
                roomNo.requestFocus()
            }
            else{

                val data= hashMapOf(
                    "Name" to name.text?.trim().toString(),
                    "Roll No" to roll.text?.trim().toString(),
                    "Hostel" to hostel.text?.trim().toString(),
                    "Room No" to roomNo.text?.trim().toString(),
                    "ProfileCreated" to "1",
                    "Profile Verified" to "0"

                )
                db.collection("Users").document("$uid")
                    .set(data)
                    .addOnSuccessListener {docRef ->
                        Log.d("Data Addition", "DocumentSnapshot written with ID: ${docRef}.id")
                        Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Log.w("Data Addition", "Error adding document", e)
                    }
            }
        }
    }
}