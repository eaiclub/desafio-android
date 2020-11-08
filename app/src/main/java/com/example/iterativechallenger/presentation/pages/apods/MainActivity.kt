package com.example.iterativechallenger.presentation.pages.apods

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.iterativechallenger.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.iterativechallenger.core.utils.Response
import com.example.iterativechallenger.core.utils.Status
import com.example.iterativechallenger.domain.entities.Apod
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.activity_main.*



@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.response().observe(this, Observer { response -> processResponse(response) })

        viewModel.getApods()

    }

    private fun processResponse(response : Response){
        when (response.status) {
            Status.LOADING -> showLoading()
            Status.SUCCESS -> showApods(response.data)
            Status.ERROR -> showError(response.error)
        }
    }

    private fun showApods(data: Any?) {

        progress_bar.visibility = View.GONE

        if(data is List<*>) {
//            Toast.makeText(this, "deu bom, tamanho: ${data.size}", Toast.LENGTH_SHORT).show()

            val layoutManager = GridLayoutManager(this, 2)
            rv_apods.layoutManager = layoutManager
            rv_apods.adapter = ApodAdapter(data as List<Apod>, ::onMoreClick)

        }

    }

    private fun showLoading(){

        progress_bar.visibility = View.VISIBLE

    }

    private fun showError(error: Throwable?) {

        progress_bar.visibility = View.GONE
        Toast.makeText(this, "Erro ao baixar informações", Toast.LENGTH_SHORT).show()

    }

    private fun onMoreClick(apod : Apod){
        Toast.makeText(this, apod.title, Toast.LENGTH_SHORT).show()
    }
}