package com.scz.planetary.presentation.design

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetaryFilterChip(
    text: String,
    selected: String,
    onSelected: () -> Unit
) {
    val isSelected = text == selected

    FilterChip(
        onClick = {
            onSelected()
        },
        label = {
            MediumBodyOnSurface(text = text)
        },
        selected = isSelected,
        leadingIcon = if (isSelected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "null",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}

@Composable
fun PlanetaryFilterChipGroup(chips: List<PlanetaryFilterChipState>) {
    var selected by remember { mutableStateOf("") }

    for (chip in chips) {
        PlanetaryFilterChip(text = chip.text, selected) {
            selected = chip.text
            chip.onSelected()
        }
    }
}

data class PlanetaryFilterChipState(
    val text: String,
    val onSelected: () -> Unit
)