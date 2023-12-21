package com.demoapp.development

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun hasText(@StringRes resId: Int) = hasText(composeTestRule.activity.getString(resId))

    @Test
    fun shouldDisplayLoginScreenWithEmailPasswordAndLoginButton_whenScreenIsLaunched() {
        composeTestRule.apply {
            onNode(hasText(R.string.action_login)).assertIsDisplayed()
            onNode(hasText(R.string.action_login)).performClick()

            // Assert elements on the new screen

        }

    }


}