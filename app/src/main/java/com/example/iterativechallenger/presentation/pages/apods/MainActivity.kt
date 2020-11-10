package com.example.iterativechallenger.presentation.pages.apods

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.iterativechallenger.R
import com.example.iterativechallenger.core.utils.Response
import com.example.iterativechallenger.core.utils.Status
import com.example.iterativechallenger.domain.entities.Apod
import com.example.iterativechallenger.presentation.pages.photo.PhotoActivity
import com.example.iterativechallenger.presentation.widgets.InfiniteScrollListener
import com.github.chrisbanes.photoview.PhotoView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel



@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModel()
    private var apodAdapter : ApodAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.response().observe(this, Observer { response -> processResponse(response) })

        viewModel.getApods()

    }



    private fun makeRecyclerView(){

        val layoutManager = GridLayoutManager(this, 2)
        rv_apods.adapter = apodAdapter
        rv_apods.layoutManager = layoutManager
        rv_apods.addOnScrollListener(
                InfiniteScrollListener({
                    viewModel.loadMoreApods()
                }, layoutManager)
        )
    }
    private fun processResponse(response: Response){
        when (response.status) {
            Status.LOADING -> showLoading()
            Status.SUCCESS -> showApods(response.data)
            Status.ERROR -> showError(response.error)
        }
    }

    private fun showApods(data: Any?) {

        progress_bar.visibility = View.GONE

        if(data is List<*>) {

            if(apodAdapter == null) {
                apodAdapter = ApodAdapter(data as ArrayList<Apod>, ::onMoreClick)
                makeRecyclerView()
            }
            else
                apodAdapter?.setList(data as List<Apod>)
        }

    }

    private fun showLoading(){

        progress_bar.visibility = View.VISIBLE

    }

    private fun showError(error: Throwable?) {

        progress_bar.visibility = View.GONE
        Toast.makeText(this, "Erro ao baixar informações", Toast.LENGTH_SHORT).show()
        println(error)

    }

    private fun onMoreClick(apod: Apod){
        val intent = Intent(applicationContext, PhotoActivity::class.java)
        intent.putExtra("apod", apod)
        startActivity(intent)
    }

}