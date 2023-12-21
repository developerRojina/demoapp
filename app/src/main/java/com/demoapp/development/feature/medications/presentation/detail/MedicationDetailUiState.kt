package com.demoapp.development.feature.medications.presentation.detail

import com.demoapp.development.feature.medications.domain.model.Medication

sealed class MedicationDetailUiState {
    data class Loading(val isLoading: Boolean) : MedicationDetailUiState()
    data class Success(val medication: Medication) : MedicationDetailUiState()
    data class Error(val error: String) : MedicationDetailUiState()
}