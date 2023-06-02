package com.androrubin.messmavendemo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class TakeLeaveActivity:AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_leave)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)

        val clickBtn=findViewById<ImageView>(R.id.click_btn)
        val clickbtn2=findViewById<ImageView>(R.id.click_btn2)
        val long_btn=findViewById<Button>(R.id.long_apply)

        val textview=findViewById<TextView>(R.id.dateTv)
        clickBtn.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                textview.setText(""+mdayOfMonth+"/"+mmonth+"/"+myear)
            }, year, month, day)
            dpd.show()
        }
        val textview2=findViewById<TextView>(R.id.dateTv1)
        clickbtn2.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                textview2.setText(""+mdayOfMonth+"/"+mmonth+"/"+myear)
            }, year, month, day)
            dpd.show()
        }

        val cb1=findViewById<CheckBox>(R.id.cb1)
        val cb2=findViewById<CheckBox>(R.id.cb2)
        val cb3=findViewById<CheckBox>(R.id.cb3)
        val sing_btn=findViewById<Button>(R.id.single_btn)

        sing_btn.setOnClickListener {
            var str="You will skip "
            if(cb1.isChecked){
                str+="breakfast,"
            }
            if(cb2.isChecked){
                str+=" lunch,"
            }
            if(cb3.isChecked){
                str+=" dinner tomorrow"
            }
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
        }

        long_btn.setOnClickListener {
            var str="You will skip "
            if(cb1.isChecked){
                str+="breakfast,"
            }
            if(cb2.isChecked){
                str+=" lunch,"
            }
            if(cb3.isChecked){
                str+=" dinner tomorrow"
            }
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
        }


    }
}