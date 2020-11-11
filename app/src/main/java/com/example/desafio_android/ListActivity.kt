package com.example.desafio_android

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.stream.Collectors
import android.util.Range
import android.widget.ImageView
import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Exception
import java.util.stream.IntStream.range
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.example.desafio_android.LoadStateAdapter
import com.example.desafio_android.SimpleTextAdapter
import kotlinx.android.synthetic.main.activity_list.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

const val BASE_URL = "https://api.nasa.gov"

class ListActivity : AppCompatActivity() {

    private lateinit var simpleTextAdapter: SimpleTextAdapter
    private lateinit var loadStateAdapter: LoadStateAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setupRecyclerView()
        addItems()
    }

    private fun addItems() {
        val listDate = ArrayList<String>()
        (1..7).forEach {

            var date = Calendar.getInstance()
            val sdf= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            date.add(Calendar.DATE, -it)
            var result = sdf.format(date.time)
            listDate.add(result)

        }
        getNasaImages(listDate)
    }

    private fun getNasaImages(dates: ArrayList<String>) {
        val listImages = ArrayList<String>()
        var img = ""
        for (date in dates) {
            val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRequests::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val response = api.getPlanetary("DEMO_KEY", date).awaitResponse()
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            img = data.url
                            listImages.add(img+"")
                        }
                    }
                    simpleTextAdapter.list = listImages
                } catch (e: Exception) {
                    println(e)
                }
            }
        }


    }

    private fun setupRecyclerView() = with(recyclerView) {
        simpleTextAdapter = SimpleTextAdapter()
        loadStateAdapter = LoadStateAdapter()

        layoutManager = LinearLayoutManager(this@ListActivity)

        adapter = MergeAdapter(
            simpleTextAdapter,
            loadStateAdapter
        )

        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadStateAdapter.loadState = LoadState.Loading
                    postDelayed(
                        {
                            addItems()
                        },
                        1000
                    )
                }
            }
        })
    }
}

