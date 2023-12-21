package com.demoapp.development.feature.medications.di

import com.demoapp.development.feature.medications.data.MedicationRepositoryImpl
import com.demoapp.development.feature.medications.data.local.LocalMedicationSource
import com.demoapp.development.feature.medications.domain.mapper.MedicationMapper
import com.demoapp.development.feature.medications.domain.repository.MedicationRepository
import com.sagarmathainsurance.products.data.remote.MedicationService
import com.sagarmathainsurance.products.data.remote.RemoteMedicationSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class MedicationModules {
    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): MedicationService = retrofit.create(
        MedicationService::class.java
    )

    @Singleton
    @Provides
    fun provideMedicationRepository(
        remoteDataSource: RemoteMedicationSource,
        medicationMapper: MedicationMapper,
        localDataSource: LocalMedicationSource,


        ): MedicationRepository {
        return MedicationRepositoryImpl(
            remoteDataSource = remoteDataSource,
            medicationMapper = medicationMapper,
            localDataSource = localDataSource,

            )
    }

}