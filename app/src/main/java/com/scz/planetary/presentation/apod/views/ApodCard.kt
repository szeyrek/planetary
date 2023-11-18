package com.scz.planetary.presentation.apod.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.scz.planetary.domain.model.Apod
import com.scz.planetary.presentation.design.LargeTitleOnSurface
import com.scz.planetary.presentation.design.SmallTitleOnSurface
import com.scz.planetary.presentation.design.fillMaxWidthMediumHeight
import com.scz.planetary.presentation.design.fillMaxWidthMediumPadding
import com.scz.planetary.presentation.design.largePadding
import com.scz.planetary.presentation.design.smallPadding
import com.scz.planetary.util.toSafeString

@Composable
fun ApodCard(
    apod: Apod?,
    onItemClick: (Apod?) -> Unit
) {
    if (apod == null) return

    Card(
        modifier = fillMaxWidthMediumPadding
            .clickable {
                onItemClick(apod)
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(smallPadding)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(largePadding),
            modifier = Modifier.fillMaxWidth()
        ) {
            LargeTitleOnSurface(
                text = apod.title.toSafeString(),
                modifier = fillMaxWidthMediumPadding
            )
            SmallTitleOnSurface(text = apod.date.toSafeString())
            Image(
                modifier = fillMaxWidthMediumHeight,
                contentScale = ContentScale.FillWidth,
                painter = rememberAsyncImagePainter(apod.hdUrl), contentDescription = apod.title
            )
        }
    }
}
