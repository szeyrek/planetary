package com.scz.planetary.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Apod(
    val explanation: String?,
    val title: String?,
    val copyright: String?,
    val hdUrl: String?,
    val url: String?,
    val date: String?
) : Parcelable