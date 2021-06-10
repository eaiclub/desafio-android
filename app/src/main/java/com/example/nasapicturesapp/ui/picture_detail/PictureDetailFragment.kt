package com.example.nasapicturesapp.ui.picture_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.nasapicturesapp.databinding.FragmentPictureDetailBinding
import com.example.nasapicturesapp.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PictureDetailFragment : Fragment() {
    private lateinit var binding: FragmentPictureDetailBinding

    private val viewModel: PictureDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPictureDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initViewModel(arguments?.getSerializable("date") as String)
        viewModel.picture.observe(viewLifecycleOwner, { picture ->
            binding.pictureTitle.text = picture.title
            binding.pictureDescription.text = picture.explanation
            binding.pictureZoomable.loadUrl(picture.url)
            binding.pictureDate.text = picture.date
        })
    }
}