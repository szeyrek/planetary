package com.scz.planetary.presentation.apoddetail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import com.scz.planetary.presentation.design.fillMaxWidthLargePadding
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.scz.planetary.domain.model.Apod
import com.scz.planetary.presentation.apoddetail.ApodDetailViewModel
import com.scz.planetary.presentation.design.MediumBodyOnSurface
import com.scz.planetary.presentation.design.mediumPadding
import com.scz.planetary.util.StateKeys
import com.scz.planetary.util.toSafeString


@Composable
fun ApodDetailScreen(
    topBarTitle: (String) -> Unit,
//    topBarNavigationIcon: (@Composable () -> Unit) -> Unit,
    topBarNavigationIconClick: (() -> Unit) -> Unit,
    viewModel: ApodDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    navController.previousBackStackEntry?.savedStateHandle?.get<Apod>(StateKeys.APOD)?.let {
        LaunchedEffect(key1 = true) {
            topBarTitle(it.title.toSafeString())
//            topBarNavigationIcon {
//                Icon(
//                    imageVector = Icons.Filled.ArrowBack,
//                    contentDescription = null
//                )
//            }
            topBarNavigationIconClick {
                navController.popBackStack()
            }
        }
        viewModel.getApodDetail(it)
    }
    val apodDetailState = viewModel.apodDetailState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .align(Center)
                .padding(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = apodDetailState.url),
                contentDescription = apodDetailState.detail,
                modifier = fillMaxWidthLargePadding
                    .clip(RectangleShape)
            )
            MediumBodyOnSurface(text = apodDetailState.detail.toSafeString())
        }
    }
}