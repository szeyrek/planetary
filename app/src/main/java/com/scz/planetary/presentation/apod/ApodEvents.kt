package com.scz.planetary.presentation.apod

sealed class ApodEvents {
    object SwipeToRefresh : ApodEvents()
    class DateSelected(val startDate: String?, val endDate: String?) : ApodEvents()
    class ChipSelected(val selectedChip: Chips) : ApodEvents()
}

enum class Chips {
    LAST_WEEK, LAST_MONTH, LAST_3_MONTH
}