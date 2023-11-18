package com.scz.planetary.presentation.apoddetail

data class ApodDetailState(
    val isLoading: Boolean = false,
    val detail: String? = "",
    val url: String? = "",
    val error: String? = ""
)