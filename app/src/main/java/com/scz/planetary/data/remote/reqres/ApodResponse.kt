package com.scz.planetary.data.remote.reqres

import com.scz.planetary.domain.model.Apod

class ApodResponse : ArrayList<ApodListItem>()

data class ApodListItem(
    val copyright: String?,
    val date: String?,
    val explanation: String?,
    val hdurl: String?,
    val media_type: String?,
    val service_version: String?,
    val title: String?,
    val url: String?
)

fun ApodResponse.toDomainModel() = this.map {
    with(it) {
        Apod(explanation, title, copyright, hdurl, url, date)
    }
}