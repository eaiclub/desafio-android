package com.lucasdonato.nasa.mechanism.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

data class Resource<out DataType>(
    val status: Status, val message: String? = null,
    val errorCode: Int? = null, val data: DataType? = null, val errorMessageId: Int? = null
) {

    companion object {

        fun <DataType> loading(data: DataType? = null): Resource<DataType> = Resource(
            Status.LOADING, data = data
        )

        fun <DataType> success(data: DataType? = null): Resource<DataType> = Resource(
            Status.SUCCESS, data = data
        )

        fun <DataType> success(
            data: DataType? = null,
            status: Status
        ): Resource<DataType> = Resource(status, data = data)

        fun <DataType> error(
            message: String? = null, errorCode: Int? = null,
            errorMessageId: Int? = null, data: DataType? = null
        ): Resource<DataType> = Resource(
            Status.ERROR,
            message = message, errorCode = errorCode, errorMessageId = errorMessageId, data = data
        )
    }

    fun <NewDataType> mapData(block: (DataType?) -> NewDataType?): Resource<NewDataType> = Resource(
        status, message, errorCode, block(data)
    )

    fun mapStatusForPage(page: Int?): Resource<DataType> {
        if (page == null || page <= 1) {
            return this
        }
        return when (status) {
            Status.LOADING -> copy(status = Status.LOADING_NEXT_PAGE)
            Status.SUCCESS -> copy(status = Status.SUCCESS_NEXT_PAGE)
            Status.ERROR -> copy(status = Status.ERROR_NEXT_PAGE)
            else -> this
        }
    }

    fun isLoading(): Boolean = status == Status.LOADING || status == Status.LOADING_NEXT_PAGE
    fun isSuccess(): Boolean = status == Status.SUCCESS || status == Status.SUCCESS_NEXT_PAGE
    fun isError(): Boolean = status == Status.ERROR || status == Status.ERROR_NEXT_PAGE
    fun isSuccessOrError(): Boolean = isSuccess() || isError()

}

enum class Status {
    LOADING,
    SUCCESS,
    EMPTY,
    ERROR,
    RELOADING,
    LOADING_NEXT_PAGE,
    SUCCESS_NEXT_PAGE,
    ERROR_NEXT_PAGE
}

typealias LiveDataResource<DataType> = LiveData<Resource<DataType>>

typealias MutableLiveDataResource<DataType> = MutableLiveData<Resource<DataType>>

typealias MediatorLiveDataResource<DataType> = MediatorLiveData<Resource<DataType>>

typealias ObserverResource<DataType> = Observer<Resource<DataType>>