package com.example.nasapicturesapp.ui.picture_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.databinding.FragmentPictureDetailBinding
import com.example.nasapicturesapp.util.loadUrl
import com.example.nasapicturesapp.util.toUserFriendlyDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PictureDetailFragment : Fragment() {
    private lateinit var binding: FragmentPictureDetailBinding

    private val viewModel: PictureDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initViewModel(arguments?.getSerializable("date") as String)
    }

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
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.picture.observe(viewLifecycleOwner, { picture ->
            binding.pictureTitle.text = picture.title
            binding.pictureDescription.text = picture.explanation
            binding.pictureDate.text = picture.date.toUserFriendlyDate()
            binding.pictureImageView.loadUrl(picture.url)
            binding.pictureImageView.setOnClickListener {
                val bundle = bundleOf("url" to picture.url)
                findNavController().navigate(R.id.action_pictureDetailFragment_to_imageViewerFragment, bundle)
            }
        })
    }
}