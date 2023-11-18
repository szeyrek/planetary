package com.scz.planetary.domain.repository

import com.scz.planetary.data.remote.reqres.ApodResponse

interface ApodRepository {

    suspend fun getApod(
        startDate: String?,
        endDate: String?,
        count: String?
    ): ApodResponse
}