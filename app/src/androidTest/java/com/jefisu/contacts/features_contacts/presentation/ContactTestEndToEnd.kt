package com.jefisu.contacts.features_contacts.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jefisu.contacts.core.MainActivity
import com.jefisu.contacts.core.presentation.components.Navigation
import com.jefisu.contacts.core.presentation.ui.theme.ContactsTheme
import com.jefisu.contacts.core.presentation.util.TestTag
import com.jefisu.contacts.features_contacts.di.AppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@HiltAndroidTest
@UninstallModules(AppModule::class)
class ContactTestEndToEnd {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    val phone = Random.nextLong(1000000000000, 9999999999999)

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberAnimatedNavController()
            ContactsTheme(true) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Navigation(navController = navController)
                }
            }
        }
    }

    @Test
    fun saveContact_editAfterwards() {
        // Go to add a new contact
        composeRule.onNodeWithTag(TestTag.FAB).performClick()

        // Filling in the fields
        composeRule
            .onNodeWithTag(TestTag.FIELD_NAME)
            .performTextInput("test-name")
        composeRule
            .onNodeWithTag(TestTag.FIELD_PHONE)
            .performTextInput(phone.toString())

        // Click button to save and go back to home screen
        composeRule.onNodeWithText("Save").performClick()

        // Checks if the contact has been entered
        composeRule.onNodeWithText("test-name").assertIsDisplayed()

        // Show contact information
        composeRule.onNodeWithText("test-name").performClick()

        // Click on contact to edit it
        composeRule
            .onNodeWithContentDescription("Edit contact information")
            .performClick()

        // Add changes in all fields
        composeRule
            .onNodeWithTag(TestTag.FIELD_NAME)
            .performTextInput("test-name2")
        composeRule
            .onNodeWithTag(TestTag.FIELD_PHONE)
            .performTextInput(phone.toString())
        composeRule
            .onNodeWithTag(TestTag.FIELD_EMAIL)
            .performTextInput("test2@test.com")

        // Update contact info
        composeRule.onNodeWithText("Save").performClick()

        // Make sure the update was applied
        composeRule.onNodeWithText("test-name2").assertIsDisplayed()
    }
}