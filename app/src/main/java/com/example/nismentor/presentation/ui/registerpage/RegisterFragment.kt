package com.example.nismentor.presentation.ui.registerpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.nismentor.R

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menti_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rdButton = view.findViewById<RadioButton>(R.id.mentor)
        if(rdButton.isChecked) {

        }
        else {

        }

//        val viewpager = view.findViewById<ViewPager2>(R.id.view_pager)
//        val adapter = RegisterAdapter(childFragmentManager, lifecycle)
//        viewpager.adapter = adapter
//
//        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
//        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
//            when (position) {
//                0 -> tab.text = "MENTI"
//                1 -> tab.text = "MENTOR"
//            }
//        }.attach()

    }
}