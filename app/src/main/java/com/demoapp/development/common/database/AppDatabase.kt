package org.lotka.bp.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demoapp.development.feature.medications.data.local.MedicationDao
import com.demoapp.development.feature.medications.data.local.MedicationEntity

@Database(entities = [MedicationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medicationDao(): MedicationDao

    companion object {
        val DATABASE_NAME: String = "demo_db"
    }

}