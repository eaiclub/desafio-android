package com.example.nasapicturesapp.ui.picture_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.databinding.FragmentImageViewerBinding
import com.example.nasapicturesapp.util.loadUrl

class ImageViewerFragment : Fragment() {
    private lateinit var binding: FragmentImageViewerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageViewerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getSerializable("url") as String?
        if(url.isNullOrEmpty()) {
            binding.picturePhotoView.setImageResource(R.drawable.ic_round_image_24)
        } else {
            binding.picturePhotoView.loadUrl(url)
        }
    }
}