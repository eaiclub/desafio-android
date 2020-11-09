package com.example.infinitescroll.presentation.util

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.infinitescroll.data.model.Apod
import retrofit2.HttpException
import java.io.IOException
import java.util.*

/**
 * Mediator to decide if data is on local database or need to be fetched from network.
 */
@OptIn(ExperimentalPagingApi::class)
class ApodRemoteMediator(private val loadFromNetwork : suspend (Calendar?) -> Unit) : RemoteMediator<Int, Apod>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Apod>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    lastItem.date
                }
            }
            loadFromNetwork.invoke(loadKey)

            MediatorResult.Success(
                endOfPaginationReached = false
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}