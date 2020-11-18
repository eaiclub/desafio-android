package com.lucasdonato.nasa.data.remote.dataSource

import com.lucasdonato.nasa.data.remote.WebService

class ApodDataSource (private val webService: WebService){

    fun getApod(startDate: String, endDate: String) = webService.getList(startDate, endDate)

}