package com.example.desafio_android

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.stream.Collectors
import android.util.Range
import com.github.kittinunf.fuel.Fuel
import java.util.stream.IntStream.range

class ListActivity : AppCompatActivity() {
    private var list: MutableList<Int> = mutableListOf()
    private var lastItem: Int = 0
    private val listAdapter: NumberListAdapter = NumberListAdapter(::onBottomListener)
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        Fuel.get("https://httpbin.org/get")
            .response { request, response, result ->
                println(request)
                println(response)
                val (bytes, error) = result
                if (bytes != null) {
                    println("[response bytes] ${String(bytes)}")
                }
            }

        recyclerView = findViewById(R.id.recycler_view_list)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = listAdapter

        updateList()
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
        val next = lastItem + 20
        //val list: List<Int> = range(lastItem, next).boxed().collect(Collectors.toList())
        val range = range(lastItem, next).boxed().collect(Collectors.toList())
        lastItem = next
        list.addAll(range)
    }
}

