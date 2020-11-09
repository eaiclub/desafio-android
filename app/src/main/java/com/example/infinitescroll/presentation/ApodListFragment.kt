package com.example.infinitescroll.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.infinitescroll.databinding.FragmentApodListBinding
import com.example.infinitescroll.presentation.adapter.ApodAdapter
import com.example.infinitescroll.presentation.viewmodel.ApodListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.RecyclerView
import com.example.infinitescroll.presentation.util.ApodRemoteMediator

@AndroidEntryPoint
class ApodListFragment : Fragment() {

    private val adapter = ApodAdapter().also { it.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY }
    private val viewModel : ApodListViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentApodListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.let {
            it.lifecycleOwner = this
        }

        binding.recycler.let {
            it.adapter = adapter
            it.setHasFixedSize(true)
            it.setItemViewCacheSize(20)
        }

        lifecycleScope.launch {
            val pagingSource = viewModel.getApods()
            val page = Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = ApodListViewModel.PAGESIZE),
                remoteMediator = ApodRemoteMediator { calendar ->  viewModel.loadApods(calendar)},
                pagingSourceFactory = pagingSource.asPagingSourceFactory()
            )
            page.flow.collectLatest {
                adapter.submitData(it)
            }
        }

        return binding.root
    }
}