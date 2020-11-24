package com.example.desafio_android.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.desafio_android.R
import com.example.desafio_android.common.adapters.ApodListAdapter
import com.example.desafio_android.common.domain.models.NasaApod
import com.example.desafio_android.databinding.FragmentApodListBinding
import com.example.desafio_android.ui.NasaViewModel
import com.example.desafio_android.util.*
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ApodListFragment : Fragment(), IOnClickListener {

    private val viewModel: NasaViewModel by sharedViewModel()
    private var binding: FragmentApodListBinding? = null
    private var adapter: ApodListAdapter? = null

    private val observer = Observer<Resource<List<NasaApod>>> {
        when (it.status) {
            Status.SUCCESS -> {
                binding?.loading?.visibility = View.INVISIBLE
                loadRecycler(it.data!!)
            }
            Status.ERROR   -> { showDialog(it.status.toString() , it.message ?: "Ops, Somenthing went wrong") }
            Status.LOADING -> {
                if (adapter == null) {
                 binding ?. loading ?. visibility = View . VISIBLE
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_apod_list, container, false)
        binding?.viewModel = viewModel
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getApodLiveData().observe(viewLifecycleOwner, observer)
        val hasListOnMemory: Boolean = viewModel.getApodListFromMemory().size > 0

        if (hasListOnMemory){
            viewModel.loadApodsFromMemory()
        }else{
            viewModel.loadApodList()
        }

        super.onViewCreated(view, savedInstanceState)
    }


    private fun loadRecycler(apodsList: List<NasaApod>){
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding?.apodListRecycler?.layoutManager = layoutManager
        if (adapter != null) {
            adapter?.addMoreItems(apodsList)
        } else {
            adapter = ApodListAdapter(apodsList, this)
            setUpScrollListener(layoutManager)
        }
        binding?.apodListRecycler?.adapter = adapter
    }

    private fun setUpScrollListener(layoutManager: StaggeredGridLayoutManager) {
        val scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                adapter?.setLoading()
                viewModel.loadApodList(totalItemsCount)
                adapter?.setLoaded()
            }
        }
        binding?.apodListRecycler?.addOnScrollListener(scrollListener)
    }

    override fun onClick(apod: NasaApod) {
        val navigateToApodDetails =  ApodListFragmentDirections.actionApodListFragmentToApodDisplayFragment(apod)
        findNavController().navigate(navigateToApodDetails)
    }

    private fun showDialog(header: String, body: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context!!)
        builder.setTitle(header)
        builder.setMessage(body)
        builder.setPositiveButton("close") { dialogInterface, i ->
            Snackbar.make(
                binding?.root!!,
                "",
                Snackbar.LENGTH_SHORT
            )
        }
        builder.show()
    }


}