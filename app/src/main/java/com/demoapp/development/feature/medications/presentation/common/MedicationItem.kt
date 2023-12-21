package com.demoapp.development.feature.medications.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.demoapp.development.feature.medications.domain.model.Medication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationItem(medication: Medication, onMedicationClick: (id: Long) -> Unit) {
    Card(
        onClick = { onMedicationClick(medication.id) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Name: ${medication.name}")
            Text(text = "Dose: ${medication.dose}")
            Text(text = "Strength: ${medication.strength}")
        }
    }
}