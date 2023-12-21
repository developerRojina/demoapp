package com.demoapp.development.feature.medications.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demoapp.development.feature.medications.presentation.common.MedicationItem
import com.demoapp.development.feature.medications.presentation.medication_list.MedicationListUiState
import timber.log.Timber

@Composable
fun MedicationDetailScreen(viewModel: MedicationDetailViewModel = hiltViewModel()) {


    val medicationUiState by viewModel.medicationDetailUiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (medicationUiState) {
            is MedicationDetailUiState.Loading -> {
                Text(text = "Loading")
            }

            is MedicationDetailUiState.Success -> {
                Timber.d("inside success")
                val medication = (medicationUiState as MedicationDetailUiState.Success).medication
                MedicationItem(medication) { id ->
                    {
                        //Do nothing already in detail
                    }
                }

            }

            is MedicationDetailUiState.Error -> {
                val error = (medicationUiState as MedicationListUiState.Error).error
                Text(text = "Error")
            }
        }
    }
}