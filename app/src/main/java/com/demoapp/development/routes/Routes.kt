package com.demoapp.development.routes


sealed class Routes(val route: String) {
    data object Login : Routes("login")
    data object Medications : Routes("medications/{username}")
    data object MedicationDetail : Routes("medication_detail/{id}")
}
