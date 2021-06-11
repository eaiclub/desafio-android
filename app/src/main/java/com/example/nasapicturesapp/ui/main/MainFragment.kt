package com.example.nasapicturesapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.databinding.MainFragmentBinding
import com.example.nasapicturesapp.ui.main.adapter.PicturesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var picturesAdapter: PicturesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        picturesAdapter = PicturesAdapter {
            val bundle = bundleOf("date" to it.date)
            findNavController().navigate(R.id.action_mainFragment_to_pictureDetailFragment, bundle)
        }

        binding.picturesList.apply {
            adapter = picturesAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pictures.collectLatest {
                picturesAdapter.submitData(lifecycle, it)
                picturesAdapter.notifyDataSetChanged()
            }
        }
    }
}