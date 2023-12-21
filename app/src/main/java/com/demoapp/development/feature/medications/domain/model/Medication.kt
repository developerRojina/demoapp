package com.demoapp.development.feature.medications.domain.model

data class Medication(
    val id:Long,
    val name: String,
    val dose: String,
    val strength: String
)