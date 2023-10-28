package com.example.assigmenttestvascomm.ui.home.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.assigmenttestvascomm.R
import com.example.assigmenttestvascomm.databinding.ActivityHomeBinding
import com.example.assigmenttestvascomm.ui.home.view.menu.MenuFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fragmentContainerView.visibility = View.INVISIBLE

        binding.imgIcMenu.setOnClickListener {
            binding.fragmentContainerView.visibility = View.VISIBLE

            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, 0)
            fragmentTransaction.replace(
                R.id.fragment_container_view,
                MenuFragment()
            )
            fragmentTransaction.commit()
        }


    }

}