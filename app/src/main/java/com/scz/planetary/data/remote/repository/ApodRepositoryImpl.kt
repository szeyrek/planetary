package com.scz.planetary.data.remote.repository

import com.scz.planetary.data.remote.service.ApodService
import com.scz.planetary.domain.repository.ApodRepository
import javax.inject.Inject

class ApodRepositoryImpl @Inject constructor(private val service: ApodService) : ApodRepository {
    override suspend fun getApod(
        startDate: String?,
        endDate: String?,
        count: String?
    ) = service.getApod(startDate = startDate, endDate = endDate, count = count)
}