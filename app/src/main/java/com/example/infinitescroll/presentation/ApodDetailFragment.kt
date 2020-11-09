package com.example.infinitescroll.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.infinitescroll.databinding.FragmentApodDetailBinding
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

        viewModel.bound(args.apod)

        return binding.root
    }
}