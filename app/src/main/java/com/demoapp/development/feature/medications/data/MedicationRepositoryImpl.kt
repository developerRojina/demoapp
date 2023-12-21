package com.demoapp.development.feature.medications.data

import com.demoapp.development.feature.medications.data.local.LocalMedicationSource
import com.demoapp.development.feature.medications.domain.mapper.MedicationMapper
import com.demoapp.development.feature.medications.domain.model.Medication
import com.demoapp.development.feature.medications.domain.repository.MedicationRepository
import com.sagarmathainsurance.products.data.remote.RemoteMedicationSource
import javax.inject.Inject

class MedicationRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteMedicationSource,
    val medicationMapper: MedicationMapper,
    private val localDataSource: LocalMedicationSource
) : MedicationRepository {

    override suspend fun getMedications(): List<Medication> {
        val response = remoteDataSource.getMedications()
        val domainResponse = medicationMapper.mapRemoteMedicationListToDomain(response)
        localDataSource.insertMedications(domainResponse)
        return medicationMapper.mapDomainsToUi(domainResponse);
    }

    override suspend fun getMedicationDetail(id: Long): Medication {
        return medicationMapper.mapDomainToUi(localDataSource.getMedicationDetail(id)!!)
    }

}