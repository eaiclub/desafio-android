package br.com.versa.presentation.base.presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import br.com.versa.controller.executor.ExecutorCoroutineScope
import br.com.versa.controller.executor.getCoroutineScope

abstract class BasePresenter : ExecutorCoroutineScope by getCoroutineScope() {

    @CallSuper
    fun onCreate(savedInstanceState: Bundle?) {
    }

    @CallSuper
    fun onStart() {
    }

    @CallSuper
    fun onResume() {
    }

    @CallSuper
    fun onPause() {
    }

    @CallSuper
    fun onStop() {
    }

    @CallSuper
    fun onSaveInstanceState(outState: Bundle) {
    }

    @CallSuper
    fun onDestroy() {
    }

    @CallSuper
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    companion object {

        @JvmStatic
        fun nullPresenter(context: Context): BasePresenter {
            return object : BasePresenter() {

            }
        }
    }
}