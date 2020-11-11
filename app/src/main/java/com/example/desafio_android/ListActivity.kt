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
//
//class ListActivity : AppCompatActivity() {
//    //private var list: MutableList<Int> = mutableListOf()
//    private var list: MutableList<String> = mutableListOf<String>()
//    private var lastItem: Int = 0
//    private val listAdapter: NumberListAdapter = NumberListAdapter(::onBottomListener)
//    private lateinit var recyclerView: RecyclerView
//
//    private var TAG = "ListActivity"
//    private var img = ""
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_list)
//
//        //Oficial
//        //getCurrentData()
//
//        recyclerView = findViewById(R.id.recycler_view_list)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = listAdapter
//
//        updateList()
//    }
//
//    private fun getCurrentData() {
//        println("ENTREI NO GET CURRENT DATA")
//        val api = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiRequests::class.java)
//
//        println("JA CONSULTEI A API")
//
//        GlobalScope.launch(Dispatchers.IO) {
//            println("ENTREI NO GLOBAL SCOPE")
//            try {
//                val response = api.getPlanetary("DEMO_KEY", "2020-11-10").awaitResponse()
//                if (response.isSuccessful) {
//                    val data = response.body()
//                    if (data != null) {
//                        println("data.url"+data.url)
//                        Log.d(TAG, data.url)
//                        img = data.url
//                        println("IMG "+img)
//                    }
//                }
//            } catch (e: Exception) {
//                println("DEEEEUUU EERRROOOO")
//            }
//        }
//    }
//
//    private fun onBottomListener() {
//        Handler().postDelayed({ updateList() }, 2000)
//    }
//
//    private fun updateList() {
//        generateMore()
//        listAdapter.setList(list)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.N)
//    private fun generateMore() {
//        getCurrentData()
//        val next = lastItem + 20
//        val range = range(lastItem, next).boxed().collect(Collectors.toList())
//        lastItem = next
//        //list.addAll(range)
//
//        val arrayList: ArrayList<String> = ArrayList<String>()
//        arrayList.add(img)
//        list.addAll(arrayList)
//    }
//}

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
            println("result $it "+result)
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
                            println("data.url"+data.url)
                            img = data.url
                            println("IMG "+img)
                            listImages.add(img+"")
                            println("LIST IMAGES "+listImages)
                        } else {
                            println("is null")
                        }
                    } else {
                        println("not sucessful")
                    }
                    simpleTextAdapter.list = listImages
                    //showNasaImages(listImages)
                } catch (e: Exception) {
                    println(e)
                }
            }
        }


    }

    private fun showNasaImages(images: ArrayList<String>) {
        println("IMAGES "+images)
        simpleTextAdapter.list = images
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

