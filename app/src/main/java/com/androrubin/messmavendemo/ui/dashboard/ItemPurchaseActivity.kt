package com.androrubin.messmavendemo.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.androrubin.messmavendemo.R
import com.bumptech.glide.Glide

class ItemPurchaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_purchase)

        val item_name=findViewById<TextView>(R.id.itemName)
        val item_price=findViewById<TextView>(R.id.itemPrice)
        val item_image=findViewById<ImageView>(R.id.food1)

        val name=intent.getStringExtra("Name")
        val price=intent.getStringExtra("Price")
        val image=intent.getStringExtra("Image")

        item_name.setText(name)
        item_price.text=price
       // Glide.with(this).load(image).into(item_image)

        val nextbtn=findViewById<Button>(R.id.btnSave)
        nextbtn.setOnClickListener {
            val intent= Intent(this,TransactionDoneActivity ::class.java)
            startActivity(intent)
        }
    }
}