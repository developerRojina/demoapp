package com.demoapp.development.feature.medications.presentation.medication_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demoapp.development.feature.medications.domain.model.Medication
import com.demoapp.development.feature.medications.domain.repository.MedicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MedicationsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val medicationRepository: MedicationRepository
) : ViewModel() {

    private val _medicationListUiState =
        MutableStateFlow<MedicationListUiState>(MedicationListUiState.Loading(true))

    val medicationUiState: StateFlow<MedicationListUiState>
        get() = _medicationListUiState.asStateFlow()

    init {
        viewModelScope.launch {
            val item = medicationRepository.getMedications()
            setUpMedicationList(item)
        }
    }

    fun setUpMedicationList(items: List<Medication>) {
        _medicationListUiState.value = MedicationListUiState.Success(items)
    }

    fun getMessage(): String {
        val username: String? = savedStateHandle["username"]
        val greeting = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good morning"
            in 12..17 -> "Good afternoon"
            else -> "Good evening"
        }
        val message = if (username.isNullOrEmpty() || username == "{username}") {
            "${greeting}, Welcome to the app"
        } else {
            "$greeting $username, Welcome to the app"
        }
        return message;
    }

}