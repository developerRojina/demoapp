package com.demoapp.development.feature.medications.domain.repository

import com.demoapp.development.feature.medications.domain.model.Medication

interface MedicationRepository {

    suspend fun getMedications(): List<Medication>
    suspend fun getMedicationDetail(id: Long): Medication


}