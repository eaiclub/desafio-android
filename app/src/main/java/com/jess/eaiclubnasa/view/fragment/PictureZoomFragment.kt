package com.jess.eaiclubnasa.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler
import com.bumptech.glide.Glide
import com.jess.eaiclubnasa.Constants
import com.jess.eaiclubnasa.R
import kotlinx.android.synthetic.main.fragment_picture_zoom.*

class PictureZoomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_picture_zoom, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apodImage = arguments?.getString(Constants.APOD_IMAGE_KEY)

        context?.let {
            Glide.with(it).load(apodImage).placeholder(R.drawable.logo)
                .into(iv_apod_image_zoom)
        }

        iv_apod_image_zoom.setOnTouchListener(ImageMatrixTouchHandler(view.context))

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavHostFragment.findNavController(this).popBackStack()
            }
        }
        return true
    }
}