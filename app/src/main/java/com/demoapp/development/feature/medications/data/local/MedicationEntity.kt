package com.demoapp.development.feature.medications.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medications")
data class MedicationEntity(
    @PrimaryKey val id: Long = System.nanoTime(),
    val name: String,
    val dose: String,
    val strength: String,
    val problem: String
)