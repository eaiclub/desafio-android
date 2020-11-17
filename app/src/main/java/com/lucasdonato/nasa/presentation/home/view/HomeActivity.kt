package com.lucasdonato.nasa.presentation.home.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import com.lucasdonato.nasa.R
import com.lucasdonato.nasa.mechanism.extensions.toast
import com.lucasdonato.nasa.mechanism.livedata.Status
import com.lucasdonato.nasa.presentation.home.adapter.ApodRecyclerAdapter
import com.lucasdonato.nasa.presentation.home.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    private val presenter: HomePresenter by inject { parametersOf(this) }
    private val adapterApod: ApodRecyclerAdapter by lazy {
        ApodRecyclerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupObserver()
        setupRecyclerView()
        presenter.getList("2020-11-15", "2020-11-16")
    }

    private fun setupRecyclerView() {
        apod_recycler.apply {
            adapter = adapterApod
            isFocusable = false
        }
    }

    private fun setupObserver() {
        presenter.getListLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    loader.visibility = GONE
                    it.data?.let {
                        adapterApod.data = it.toMutableList()
                    }
                }
                Status.ERROR -> setupErrorToast()
                Status.LOADING -> loader.visibility = VISIBLE
                else -> setupErrorToast()
            }
        })
    }

    private fun setupErrorToast() {
        loader.visibility = GONE
        toast(getString(R.string.error_toast_request))
    }

}