package com.example.iterativechallenger.presentation.pages.apods

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.iterativechallenger.R
import androidx.lifecycle.Observer
import com.example.iterativechallenger.core.utils.Response
import com.example.iterativechallenger.core.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

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

        if(data is List<*>) {
            Toast.makeText(this, "deu bom, tamanho: ${data.size}", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showLoading(){

        Toast.makeText(this, "Carregando", Toast.LENGTH_SHORT).show()

    }

    private fun showError(error: Throwable?) {

        Toast.makeText(this, "Erro ao baixar informações", Toast.LENGTH_SHORT).show()

    }
}