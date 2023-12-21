package com.demoapp.development.feature.medications.presentation.medication_list

import com.demoapp.development.feature.medications.domain.model.Medication

sealed class MedicationListUiState {
    data class Loading(val isLoading: Boolean) : MedicationListUiState()
    data class Success( val medications: List<Medication>) :
        MedicationListUiState()

    data class Error(val error: String) : MedicationListUiState()
}