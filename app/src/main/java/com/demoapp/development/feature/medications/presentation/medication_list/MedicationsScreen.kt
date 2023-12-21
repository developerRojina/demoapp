package com.demoapp.development.feature.medications.presentation.medication_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demoapp.development.feature.medications.presentation.common.MedicationItem

@Composable
fun MedicationsScreen(
    viewModel: MedicationsViewModel = hiltViewModel(), onNavigateToDetail: (id: Long) -> Unit
) {
    val medicationUiState by viewModel.medicationUiState.collectAsState()

    // Simulating data loading
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {

        Text(
            text = viewModel.getMessage(),
            style = MaterialTheme.typography.bodyLarge
        )

        when (medicationUiState) {
            is MedicationListUiState.Loading -> {

                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }

            is MedicationListUiState.Success -> {
                val medications = (medicationUiState as MedicationListUiState.Success).medications
                Column {

                    LazyColumn {
                        items(medications) { medication ->
                            MedicationItem(medication) { id ->
                                onNavigateToDetail(id)
                            }
                        }
                    }
                }

            }

            is MedicationListUiState.Error -> {
                val error = (medicationUiState as MedicationListUiState.Error).error
                Text(text = "Error")
            }
        }
    }
}

