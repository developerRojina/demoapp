package com.demoapp.development.feature.medications.presentation.medication_list

import androidx.lifecycle.SavedStateHandle
import com.demoapp.development.feature.medications.domain.model.Medication
import com.demoapp.development.feature.medications.domain.repository.MedicationRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Calendar

@ExperimentalCoroutinesApi
class MedicationsViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }


    @Test
    fun `getMessage returns correct message when username is provided`() {
        // Given
        val username = "John"
        val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
        coEvery { savedStateHandle.get<String>("username") } returns username
        val medicationRepository = mockk<MedicationRepository>()
        val viewModel = MedicationsViewModel(savedStateHandle, medicationRepository)

        // When
        val message = viewModel.getMessage()

        // Then
        val expectedGreeting = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good morning"
            in 12..17 -> "Good afternoon"
            else -> "Good evening"
        }
        val expectedMessage = "$expectedGreeting $username, Welcome to the app"
        assertEquals(expectedMessage, message)
    }

    @Test
    fun `getMessage returns correct message when username is null or empty`() {
        // Given
        val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)
        coEvery { savedStateHandle.get<String>("username") } returns null
        val medicationRepository = mockk<MedicationRepository>()
        val viewModel = MedicationsViewModel(savedStateHandle, medicationRepository)

        // When
        val message = viewModel.getMessage()

        // Then
        val expectedGreeting = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good morning"
            in 12..17 -> "Good afternoon"
            else -> "Good evening"
        }
        val expectedMessage = "$expectedGreeting, Welcome to the app"
        assertEquals(expectedMessage, message)
    }


}