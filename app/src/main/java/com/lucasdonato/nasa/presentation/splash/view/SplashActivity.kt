package com.lucasdonato.nasa.presentation.splash.view

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.lucasdonato.nasa.R
import com.lucasdonato.nasa.presentation.home.view.HomeActivity
import com.lucasdonato.nasa.presentation.onboarding.view.OnboardingActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        showLogoNasaSplash()
    }

    private fun showLogoNasaSplash() {
        logo_nasa.addAnimatorListener(object :
            Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {}

            override fun onAnimationEnd(animator: Animator) {
                startWelcomeScreen()
            }

            override fun onAnimationCancel(animator: Animator) {}
            override fun onAnimationRepeat(animator: Animator) {}
        })
    }

    private fun startWelcomeScreen() {
        startActivity(OnboardingActivity.getStartIntent(this))
        finish()
    }
}