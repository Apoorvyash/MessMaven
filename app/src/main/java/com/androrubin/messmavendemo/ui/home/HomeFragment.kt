package com.androrubin.messmavendemo.ui.home

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androrubin.messmavendemo.R
import com.androrubin.messmavendemo.TakeLeaveActivity
import com.androrubin.messmavendemo.databinding.FragmentHomeBinding
import com.androrubin.messmavendemo.ui.dashboard.adapters.VPAdapter
import com.androrubin.messmavendemo.ui.dashboard.fragments.QrScan
import com.google.firebase.database.collection.LLRBNode.Color

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.announcementsBtn.setOnClickListener {
            startActivity(Intent(context,Announcements::class.java))

        }
        binding.sickBtn.setOnClickListener {
            val message : String? = "Do you want sick food"
            showCustomDialogBox(message)
        }
        binding.leaveBtn.setOnClickListener {
            startActivity(Intent(context,TakeLeaveActivity::class.java))
        }

        return root
    }
    private fun showCustomDialogBox(message:String?){
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.sick_food_dialog)
        //dialog.window?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        val tvMessage : TextView = dialog.findViewById(R.id.sickmessage)
        val btnYes : Button =dialog.findViewById(R.id.btnYes)
        val btnNo : Button =dialog.findViewById(R.id.btnNo)
        tvMessage.text =message

        btnYes.setOnClickListener {
            Toast.makeText(context,"Your sick food will be prepared",Toast.LENGTH_SHORT).show()
            dialog.dismiss()

        }
        btnNo.setOnClickListener {
            dialog.dismiss()

        }
        dialog.show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}