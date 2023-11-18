package com.scz.planetary.presentation

sealed class Screen(val route: String) {
    object ApodScreen : Screen("apod_screen")
    object ApodDetailScreen : Screen("apod_detail_screen")
}
