package com.lucasdonato.nasa.presentation.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasdonato.nasa.R
import com.lucasdonato.nasa.mechanism.extensions.toast
import com.lucasdonato.nasa.mechanism.livedata.Status
import com.lucasdonato.nasa.presentation.detail.view.DetailActivity
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
        catchRangeDate()
    }

    private fun setupRecyclerView() {
        LinearLayoutManager(this).let {
            apod_recycler.apply {
                adapter = adapterApod
                isFocusable = false
                it.reverseLayout = true
                it.stackFromEnd = true
                layoutManager = it
                adapterApod.onItemClickListener = {
                    startActivity(DetailActivity.getStartIntent(context, it))
                }
            }
        }
    }

    private fun catchRangeDate() {
        val finalData: Calendar? = null
        val endDate = finalData?.apply {
            this.add(Calendar.DATE, -1)
        } ?: Calendar.getInstance()
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).let {
            val endRange = it.format(endDate.time)
            val startRange = it.format(endDate.apply {
                this.add(Calendar.DATE, -20)
            }.time)
            presenter.getList(startRange, endRange)
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