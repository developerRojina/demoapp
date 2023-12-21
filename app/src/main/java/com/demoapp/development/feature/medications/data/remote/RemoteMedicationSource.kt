package com.sagarmathainsurance.products.data.remote



import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class RemoteMedicationSource @Inject constructor(private val medicationService: MedicationService) {

    suspend fun getMedications(): String = medicationService.getMedications()

}