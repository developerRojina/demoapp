package com.demoapp.development.feature.medications.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demoapp.development.feature.medications.domain.repository.MedicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MedicationDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val medicationRepository: MedicationRepository
) :
    ViewModel() {
    init {
        val id: Long = checkNotNull(savedStateHandle["id"])
        Timber.d("the id is:" + id)
        getMedicationDetail(id)

    }

    private val _medicationListUiState =
        MutableStateFlow<MedicationDetailUiState>(MedicationDetailUiState.Loading(true))

    val medicationDetailUiState: StateFlow<MedicationDetailUiState>
        get() = _medicationListUiState.asStateFlow()

    private fun getMedicationDetail(id: Long) {
        viewModelScope.launch {
            val response = medicationRepository.getMedicationDetail(id)
            _medicationListUiState.value = MedicationDetailUiState.Success(response)

        }
    }


}