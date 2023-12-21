package com.demoapp.development.feature.medications.data.local

import javax.inject.Inject

class LocalMedicationSource @Inject constructor(private val medicationDao: MedicationDao) {

    suspend fun insertMedications(medications: List<MedicationEntity>): LongArray {
        return medicationDao.insertMedications(medications = medications)
    }

    suspend fun getMedicationDetail(id: Long): MedicationEntity? {
        return medicationDao.getMedicationById(id)

    }

}
