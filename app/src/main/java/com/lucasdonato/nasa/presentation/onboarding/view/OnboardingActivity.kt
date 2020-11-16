package com.lucasdonato.nasa.presentation.onboarding.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucasdonato.nasa.R
import com.lucasdonato.nasa.presentation.home.view.HomeActivity
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, OnboardingActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        setupListeners()
    }

    private fun setupListeners() {
        start_home_button.setOnClickListener {
            startHomeActivity()
        }
    }

    private fun startHomeActivity() {
        startActivity(HomeActivity.getStartIntent(this))
        finish()
    }

}