package com.example.infinitescroll.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.infinitescroll.R
import com.example.infinitescroll.databinding.FragmentApodDetailBinding
import com.example.infinitescroll.presentation.dialog.ImageDialog
import com.example.infinitescroll.presentation.viewmodel.ApodDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ApodDetailFragment : Fragment() {

    private val args: ApodDetailFragmentArgs by navArgs()
    private val viewModel : ApodDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentApodDetailBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }

        viewModel.apply {
            bound(args.apod)

            getOpenDialog().observe(viewLifecycleOwner, Observer {
                it?.let {
                    showDialog(it)
                }
            })

            getOpenUrl().observe(viewLifecycleOwner, Observer {
                it?.let {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(it)
                    startActivity(i)
                }
            })

            val image = getApodImage()
            if(image.isNullOrEmpty()){
                binding.image.setImageResource(R.drawable.videoplaceholder)
            } else {
                Glide.with(this@ApodDetailFragment).load(image).into(binding.image)
            }
        }

        return binding.root
    }

    private fun showDialog(image : String){
        val fragmentManager = childFragmentManager
        ImageDialog.getInstance(image).apply {
            show(fragmentManager, "")
        }
    }
}