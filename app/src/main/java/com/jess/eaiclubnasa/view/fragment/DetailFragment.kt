package com.jess.eaiclubnasa.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.bumptech.glide.Glide
import com.jess.eaiclubnasa.Constants
import com.jess.eaiclubnasa.R
import com.jess.eaiclubnasa.model.ApodResult
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view =  inflater.inflate(R.layout.fragment_detail, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showInfoDetailsApod()
    }

    private fun showInfoDetailsApod() {
        val apodResult = arguments?.getParcelable<ApodResult>(Constants.APOD_DETAIL_KEY)

        context?.let {
            Glide.with(it).load(apodResult?.url).placeholder(R.drawable.logo)
                .into(iv_apod_detail)
        }

        iv_apod_detail.setOnClickListener {
            findNavController(this).navigate(
                R.id.action_detailFragment_to_pictureZoomFragment, bundleOf(Constants.APOD_IMAGE_KEY to apodResult?.url)
            )
        }

        tv_apod_detail.text = apodResult?.title
        tv_apod_description_detail.text = apodResult?.explanation
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController(this).popBackStack()
            }
        }
        return true
    }
}