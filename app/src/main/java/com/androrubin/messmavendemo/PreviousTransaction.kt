package com.androrubin.messmavendemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class PreviousTransaction:AppCompatActivity() {

    private lateinit var PreviousTransactionList:ArrayList<PreviousTransactionData>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.previous_transaction)

        val recyclerView = findViewById<RecyclerView>(R.id.previous_rV)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        PreviousTransactionList= arrayListOf<PreviousTransactionData>()
        val list=getUserData()
        recyclerView.adapter=previousTransAdapter(list)

    }

    private fun getUserData(): ArrayList<PreviousTransactionData> {
        val date=arrayListOf<String>("Yesterday","23 Feb 2023, Tuesday","22 Feb 2023, Monday","21 Feb 2023, Sunday","20 Feb 2023, Saturday","19 Feb 2023, Friday","18 Feb 2023, Thursday","17 Feb 2023, Wednesday","16 Feb 2023, Tuesday","15 Feb 2023, Monday","14 Feb 2023, Sunday")
        for (i in 0 until 11){
            if(i==0) {
                val transaction = PreviousTransactionData(
                    "3 extra sweets + regular",
                    "1 extra curd + regular",
                    "3 eggs + regular",
                    "- Rs 60",
                    "- Rs 48",
                    " -Rs 32",
                    date.elementAt(i),
                    true
                )
                PreviousTransactionList.add(transaction)
                continue
            }

                val transaction = PreviousTransactionData(
                    "3 extra sweets + regular",
                    "1 extra curd + regular",
                    "3 eggs + regular",
                    "- Rs 60",
                    "- Rs 48",
                    " -Rs 32",
                    date.elementAt(i),
                    false
                )

            PreviousTransactionList.add(transaction)
        }
        return PreviousTransactionList
    }


}
