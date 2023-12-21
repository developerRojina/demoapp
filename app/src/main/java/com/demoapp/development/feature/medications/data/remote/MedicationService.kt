package com.sagarmathainsurance.products.data.remote

import com.demoapp.development.feature.medications.data.remote.response.MedicationResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

interface MedicationService {
    @GET("2b3ad120-f494-4ec6-be1e-a2c55540a1b1")
    suspend fun getMedications(): String
}