package com.scz.planetary.presentation.apod

import com.scz.planetary.domain.model.Apod

data class ApodState(
    val isLoading: Boolean = false,
    val apodList: List<Apod>? = null,
    val error: String = ""
)