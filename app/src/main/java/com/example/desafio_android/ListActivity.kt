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

const val BASE_URL = "https://api.nasa.gov/"

class ListActivity : AppCompatActivity() {
    //private var list: MutableList<Int> = mutableListOf()
    private var list: MutableList<String> = mutableListOf<String>()
    private var lastItem: Int = 0
    private val listAdapter: NumberListAdapter = NumberListAdapter(::onBottomListener)
    private lateinit var recyclerView: RecyclerView

    private var TAG = "ListActivity"
    private var img = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //Fuel.get("https://httpbin.org/get")
        //    .response { request, response, result ->
        //        println(request)
        //        println(response)
        //        val (bytes, error) = result
        //        if (bytes != null) {
        //            println("[response bytes] ${String(bytes)}")
        //        }
        //    }

        //Oficial
        //getCurrentData()

        //layout_generate_new_fact.setOnClickListener {
        //    getCurrentData()
        //}

        recyclerView = findViewById(R.id.recycler_view_list)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = listAdapter

        updateList()
    }

    private fun getCurrentData() {
        println("ENTREI NO GET CURRENT DATA")
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        println("JA CONSULTEI A API")

        GlobalScope.launch(Dispatchers.IO) {
            println("ENTREI NO GLOBAL SCOPE")
            try {
                val response = api.getPlanetary().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        println("data.url"+data.url)
                        Log.d(TAG, data.url)
                        img = data.url
                        println("IMG "+img)
                    }
                }
            } catch (e: Exception) {
                println("DEEEEUUU EERRROOOO")
            }
        }
    }

    private fun onBottomListener() {
        Handler().postDelayed({ updateList() }, 2000)
    }

    private fun updateList() {
        generateMore()
        listAdapter.setList(list)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun generateMore() {
        getCurrentData()
        val next = lastItem + 20
        val range = range(lastItem, next).boxed().collect(Collectors.toList())
        lastItem = next
        //list.addAll(range)

        val arrayList: ArrayList<String> = ArrayList<String>()
        arrayList.add(img)
        list.addAll(arrayList)
    }
}

