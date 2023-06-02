package com.androrubin.messmavendemo.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androrubin.messmavendemo.databinding.FragmentDashboardBinding
import com.androrubin.messmavendemo.ui.dashboard.adapters.VPAdapter
import com.androrubin.messmavendemo.ui.dashboard.fragments.QrScan

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        val tabLayout = binding.tabLayout
        val viewpager = binding.viewPager

        tabLayout?.setupWithViewPager(viewpager)

        val vpAdapter= VPAdapter(childFragmentManager)
        vpAdapter.addFragment(OrderExtra(),"Select Extra")
        vpAdapter.addFragment(QrScan(),"Scan QR")
        viewpager?.adapter = vpAdapter
        viewpager?.setSwipePagingEnabled(true)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}