package com.scz.planetary.presentation.apod

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scz.planetary.domain.usecase.GetApodUseCase
import com.scz.planetary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ApodViewModel @Inject constructor(
    private val getApodUseCase: GetApodUseCase
) : ViewModel() {

    private val _apodState = mutableStateOf(ApodState())
    val apodState: State<ApodState> = _apodState

    private var job: Job? = null

    init {
        getApod()
    }

    private fun getApod(
        startDate: String? = null,
        endDate: String? = null,
        count: String? = null
    ) {
        job?.cancel()
        job = getApodUseCase(startDate, endDate, count).onEach {
            when (it) {
                is Resource.Success -> {
                    _apodState.value = _apodState.value.copy(apodList = it.data)
                }

                is Resource.Error -> {
                    _apodState.value = _apodState.value.copy(error = it.message ?: "Error")
                }

                is Resource.Loading -> {
                    _apodState.value = _apodState.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ApodEvents) {
        when (event) {
            is ApodEvents.SwipeToRefresh -> {
                getApod()
            }

            is ApodEvents.DateSelected -> {
                getApod(event.startDate, event.endDate)
            }

            is ApodEvents.ChipSelected -> {
                when (event.selectedChip) {
                    Chips.LAST_WEEK -> {

                    }

                    Chips.LAST_MONTH -> {

                    }

                    Chips.LAST_3_MONTH -> {

                    }
                }
            }
        }
    }
}