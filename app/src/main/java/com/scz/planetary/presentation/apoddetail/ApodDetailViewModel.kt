package com.scz.planetary.presentation.apoddetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scz.planetary.domain.model.Apod
import com.scz.planetary.domain.usecase.GetApodDetailUseCase
import com.scz.planetary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ApodDetailViewModel @Inject constructor(
    private val getApodDetailUseCase: GetApodDetailUseCase
) : ViewModel() {

    private val _apodDetailState = mutableStateOf(ApodDetailState())
    val apodDetailState: State<ApodDetailState> = _apodDetailState

    fun getApodDetail(apod: Apod) {
        getApodDetailUseCase(apod).onEach {
            when (it) {
                is Resource.Loading -> {
                    _apodDetailState.value = _apodDetailState.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    _apodDetailState.value =
                        _apodDetailState.value.copy(detail = it.data?.first, url = it.data?.second)
                }

                is Resource.Error -> {
                    _apodDetailState.value = _apodDetailState.value.copy(error = "Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}