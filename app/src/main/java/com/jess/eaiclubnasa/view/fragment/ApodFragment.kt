package com.jess.eaiclubnasa.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jess.eaiclubnasa.ApodUtils
import com.jess.eaiclubnasa.Constants.APOD_DETAIL_KEY
import com.jess.eaiclubnasa.R
import com.jess.eaiclubnasa.model.ApodResult
import com.jess.eaiclubnasa.repository.ApodRepository
import com.jess.eaiclubnasa.view.adapter.ApodAdapter
import com.jess.eaiclubnasa.viewmodel.ApodViewModel
import com.jess.eaiclubnasa.viewmodel.ApodViewModelFactory
import com.jess.eaiclubnasa.viewmodel.event.ApodEvent
import com.jess.eaiclubnasa.viewmodel.interactor.ApodInteractor
import com.jess.eaiclubnasa.viewmodel.state.ApodState
import kotlinx.android.synthetic.main.fragment_apod.*
import kotlinx.coroutines.Dispatchers

class ApodFragment : Fragment() {
    private val viewModel: ApodViewModel by lazy {
        val factory = ApodViewModelFactory(Dispatchers.IO, ApodRepository())
        ViewModelProvider(this, factory).get(ApodViewModel::class.java)
    }

    private val adapter: ApodAdapter by lazy {
        ApodAdapter(
            ArrayList(),
            this::apodDetail
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_apod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_apod.adapter = adapter
        rv_apod.layoutManager = LinearLayoutManager(context)

        srl_apod.setOnRefreshListener {
            adapter.clearList()
            viewModel.interpret(ApodInteractor.GetListApod(ApodUtils.getCurrentDate()))
            srl_apod.isRefreshing = false
        }

        initViewModel()
        scrollPaginationList()
    }

    private fun initViewModel() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                when (it) {
                    is ApodState.ApodListSuccess -> showList(it.list)
                    is ApodState.ApodListError -> showError(it.messageError)
                }
            }
        })

        viewModel.viewEvent.observe(viewLifecycleOwner, Observer { event ->
            event?.let {
                when (it) {
                    is ApodEvent.Loading -> showLoading(it.status)
                }
            }
        })

        viewModel.interpret(ApodInteractor.GetListApod(ApodUtils.getCurrentDate()))
    }

    private fun showError(messageError: String) {
        Snackbar.make(rv_apod, messageError, Snackbar.LENGTH_LONG).show()
    }

    private fun showList(listApod: MutableList<ApodResult>) {
        adapter.update(listApod)
    }

    private fun apodDetail(apodResult: ApodResult) {
        val bundle = bundleOf(APOD_DETAIL_KEY to apodResult)
        findNavController(this).navigate(
            R.id.action_apodFragment_to_detailFragment, bundle
        )
    }

    private fun scrollPaginationList() {

        rv_apod.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                @NonNull recyclerView: RecyclerView, dx: Int, dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                val totalItemCount: Int = layoutManager?.itemCount ?: 0
                val lastVisible: Int = layoutManager?.findLastVisibleItemPosition() ?: 0
                val ultimoItem = lastVisible + 5 >= totalItemCount

                if (totalItemCount > 0 && ultimoItem &&
                    viewModel.viewEvent.value == ApodEvent.Loading(false)
                ) {
                    viewModel.interpret(ApodInteractor.GetListApod(adapter.getLastItem().date))
                }
            }
        })
    }

    private fun showLoading(status: Boolean) {
        pb_loading.visibility = if (status)
            View.VISIBLE
        else
            View.GONE
    }
}