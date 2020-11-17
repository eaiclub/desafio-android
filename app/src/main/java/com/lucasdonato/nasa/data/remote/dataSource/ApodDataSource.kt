package com.lucasdonato.nasa.data.remote.dataSource

import com.lucasdonato.nasa.data.remote.WebService

class ApodDataSource (private val webService: WebService){

    fun getApod(start_date: String, end_date: String) = webService.getList(start_date, end_date)

}