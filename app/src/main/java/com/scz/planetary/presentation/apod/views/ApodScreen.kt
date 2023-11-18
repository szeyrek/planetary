package com.scz.planetary.presentation.apod.views

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.scz.planetary.presentation.Screen
import com.scz.planetary.presentation.apod.ApodEvents
import com.scz.planetary.presentation.apod.ApodViewModel
import com.scz.planetary.util.convertMillisToDate
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.scz.planetary.R
import com.scz.planetary.presentation.apod.Chips
import com.scz.planetary.presentation.design.MediumBodyOnPrimary
import com.scz.planetary.presentation.design.PlanetaryFilterChipGroup
import com.scz.planetary.presentation.design.PlanetaryFilterChipState
import com.scz.planetary.presentation.design.PrimaryButton
import com.scz.planetary.presentation.design.fillMaxWidthLargePadding
import com.scz.planetary.presentation.design.mediumPadding
import com.scz.planetary.presentation.design.wrapContentMediumPadding
import com.scz.planetary.util.StateKeys

@Composable
fun ApodScreen(
    topBarTitle: (String) -> Unit,
//    topBarNavigationIcon: (@Composable () -> Unit) -> Unit,
    topBarNavigationIconClick: (() -> Unit) -> Unit,
    navController: NavController,
    viewModel: ApodViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        topBarTitle("Apods")
        topBarNavigationIconClick { }
//        topBarNavigationIcon {
//            Icon(
//                imageVector = Icons.Filled.Menu,
//                contentDescription = null
//            )
//        }
    }
    val state = viewModel.apodState.value
    val chipScrollState = rememberScrollState()
    var showDatePicker by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        if (showDatePicker) {
            ShowDatePickerDialog(onDateSelected = { startDate, endDate ->
                viewModel.onEvent(ApodEvents.DateSelected(startDate, endDate))
            }, onDismiss = { showDatePicker = false })
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = fillMaxWidthLargePadding
                    .horizontalScroll(chipScrollState, true, null, false),
                horizontalArrangement = Arrangement.spacedBy(mediumPadding)
            ) {
                PlanetaryFilterChipGroup(
                    chips = listOf(
                        PlanetaryFilterChipState(
                            text = stringResource(id = R.string.apod_list_003),
                            onSelected = {
                                viewModel.onEvent(ApodEvents.ChipSelected(Chips.LAST_WEEK))
                            }),
                        PlanetaryFilterChipState(
                            text = stringResource(id = R.string.apod_list_004),
                            onSelected = {
                                viewModel.onEvent(ApodEvents.ChipSelected(Chips.LAST_MONTH))
                            }),
                        PlanetaryFilterChipState(
                            text = stringResource(id = R.string.apod_list_005),
                            onSelected = {
                                viewModel.onEvent(ApodEvents.ChipSelected(Chips.LAST_3_MONTH))
                            })
                    )
                )
                PrimaryButton(onClick = { showDatePicker = true }) {
                    MediumBodyOnPrimary(text = stringResource(id = R.string.apod_list_001))
                }
            }
            LazyColumn(
                modifier = fillMaxWidthLargePadding
            ) {
                state.apodList?.let {
                    items(it) { apod ->
                        ApodCard(
                            apod = apod,
                            onItemClick = {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    StateKeys.APOD,
                                    apod
                                )
                                navController.navigate(Screen.ApodDetailScreen.route)
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDatePickerDialog(
    onDateSelected: (String?, String?) -> Unit,
    onDismiss: () -> Unit,
) {
    val datePickerState =
        rememberDateRangePickerState(initialSelectedStartDateMillis = System.currentTimeMillis())
    val selectedStartDate = datePickerState.selectedStartDateMillis?.convertMillisToDate()
    val selectedEndDate = datePickerState.selectedEndDateMillis?.convertMillisToDate()

    DatePickerDialog(
        onDismissRequest = { onDismiss() }, confirmButton = { }) {
        Box {
            DateRangePicker(state = datePickerState, showModeToggle = false)
            PrimaryButton(
                onClick = {
                    onDateSelected(selectedStartDate, selectedEndDate)
                    onDismiss()
                }, modifier = wrapContentMediumPadding.align(Alignment.BottomEnd)
            ) {
                MediumBodyOnPrimary(text = stringResource(id = R.string.apod_list_002))
            }
        }
    }
}