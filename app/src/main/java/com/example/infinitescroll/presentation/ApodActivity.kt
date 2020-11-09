package com.example.infinitescroll.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.infinitescroll.R
import com.example.infinitescroll.databinding.ActivityApodBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApodActivity : AppCompatActivity() {

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityApodBinding>(this, R.layout.activity_apod)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!navController.navigateUp()){
            //navigateUp() returns false if there are no more fragments to pop
            onBackPressed()
        }
        return navController.navigateUp()
    }
}