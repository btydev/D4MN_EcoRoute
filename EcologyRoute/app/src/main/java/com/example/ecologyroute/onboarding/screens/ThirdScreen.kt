package com.example.ecologyroute.onboarding.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ecologyroute.HomeAc
import com.example.ecologyroute.R
import kotlinx.android.synthetic.main.fragment_third_screen.view.*

class ThirdScreen : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_third_screen, container, false)

        view.finish.setOnClickListener {
            //findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            val intent = Intent(context, HomeAc::class.java)
            startActivity(intent)
        }
        return view
    }



}