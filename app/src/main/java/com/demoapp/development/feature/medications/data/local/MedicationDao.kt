package com.demoapp.development.feature.medications.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MedicationDao {

    @Insert
    suspend fun insertMedication(medication: MedicationEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMedications(medications: List<MedicationEntity>): LongArray

    @Query("SELECT * FROM medications WHERE id = :id")
    suspend fun getMedicationById(id: Long): MedicationEntity?

    @Query("DELETE FROM medications WHERE id IN (:ids)")
    suspend fun deleteMedications(ids: List<Long>): Int

    @Query("DELETE FROM medications")
    suspend fun deleteAllMedications()

    @Query("DELETE FROM medications WHERE id = :primaryKey")
    suspend fun deleteMedication(primaryKey: Long): Int

}