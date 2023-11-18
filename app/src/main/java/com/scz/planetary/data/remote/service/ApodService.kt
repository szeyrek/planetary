package com.scz.planetary.data.remote.service

import com.scz.planetary.data.remote.reqres.ApodResponse
import com.scz.planetary.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodService {

    @GET("planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("start_date") startDate: String?,
        @Query("end_date") endDate: String?,
        @Query("count") count: String?
    ): ApodResponse
}