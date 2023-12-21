package com.demoapp.development

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demoapp.development.feature.login.LoginScreen
import com.demoapp.development.feature.medications.presentation.detail.MedicationDetailScreen
import com.demoapp.development.feature.medications.presentation.medication_list.MedicationsScreen
import com.demoapp.development.routes.Routes
import com.demoapp.development.ui.theme.DemoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.Login.route) {
                        composable(Routes.Login.route) {
                            LoginScreen(onNavigateToHome = { username ->
                                var route = Routes.Medications.route
                                if (username.isNotEmpty()) {
                                    route = route.replace(
                                        "{username}", username
                                    )
                                }
                                navController.navigate(route)
                            })
                        }
                        composable(
                            Routes.Medications.route,
                            arguments = listOf(navArgument("username") {
                                type = NavType.StringType
                            })
                        ) {
                            MedicationsScreen(onNavigateToDetail = { id ->

                                val route = Routes.MedicationDetail.route.replace(
                                    "{id}", id.toString()
                                )
                                navController.navigate(
                                    route = route
                                )
                            })
                        }
                        composable(
                            Routes.MedicationDetail.route,
                            arguments = listOf(navArgument("id") { type = NavType.LongType })
                        ) {
                            MedicationDetailScreen()
                        }
                    }
                }
            }
        }
    }
}

