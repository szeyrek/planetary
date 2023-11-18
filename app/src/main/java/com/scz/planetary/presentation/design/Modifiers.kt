package com.scz.planetary.presentation.design

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier


val wrapContentMediumPadding = Modifier
    .wrapContentSize()
    .padding(mediumPadding)

val wrapContentLargePadding = Modifier
    .wrapContentSize()
    .padding(largePadding)

val fillMaxWidthMediumPadding = Modifier
    .fillMaxWidth()
    .padding(mediumPadding)

val fillMaxWidthLargePadding = Modifier
    .fillMaxWidth()
    .padding(largePadding)

val fillMaxWidthMediumHeight = Modifier
    .fillMaxWidth()
    .height(mediumHeight)