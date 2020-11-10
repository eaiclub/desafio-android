//package com.example.iterativechallenger.data.data_sources
//
//import androidx.paging.PagingSource
//import com.example.iterativechallenger.domain.entities.Apod
//import com.example.iterativechallenger.domain.usecases.GetListApod
//import java.util.*
//
//class PostDataSource(private val getListApod: GetListApod) : PagingSource<Pair<Calendar, Calendar> , Apod>() {
//
//    override suspend fun load(params: LoadParams<Pair<Calendar, Calendar>>): LoadResult<Pair<Calendar, Calendar>, Apod> {
//        try {
//            val currentLoadingPageKey = params.key
//            val response = getListApod(params.key!!.first, params.key!!.second)
//            val responseData = mutableListOf<Apod>()
//            response?.map {
//                responseData.add(it)
//            }
//
//            var diaFimRange = 0
//            var diaInicioRange = -10
//
//            val dataInicio = Calendar.getInstance()
//            val dataFim = Calendar.getInstance()
//
//            dataInicio.add(Calendar.DATE, diaInicioRange)
//            dataFim.add(Calendar.DATE, diaFimRange)
//
//            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
//
//            return LoadResult.Page(
//                data = responseData,
//                prevKey = prevKey,
//                nextKey = currentLoadingPageKey.plus(1)
//            )
//        } catch (e: Exception) {
//            return LoadResult.Error(e)
//        }    }
//
//}