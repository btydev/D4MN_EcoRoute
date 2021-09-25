package com.example.ecologyroute

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ecologyroute.mainfragments.AccountFragment
import com.example.ecologyroute.mainfragments.MapFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeAc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val mapFragment = MapFragment()
        val accountFragment = AccountFragment()
        makeCurrentFragment(mapFragment)
        bottom_nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_map -> makeCurrentFragment(mapFragment)
                R.id.ic_account -> makeCurrentFragment(accountFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        commit()
    }

}