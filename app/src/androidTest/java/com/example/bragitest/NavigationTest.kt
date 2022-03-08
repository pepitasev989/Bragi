package com.example.bragitest

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bragitest.ui.fragments.ForgotPasswordFragment
import com.example.bragitest.ui.fragments.LoginFragment
import com.example.bragitest.ui.fragments.SignUpFragment
import com.example.bragitest.ui.test.launchFragmentInHiltContainer
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    private val theme = R.style.Theme_BragiLight

    @Test
    fun testNavigationToSignUpScreen() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<LoginFragment>(Bundle(), theme) {
            navController.setGraph(R.navigation.navigation_graph)
            navController.setCurrentDestination(R.id.loginFragment)
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.buttonNext)).perform(ViewActions.click())
        Assert.assertEquals(navController.currentDestination?.id, R.id.signUpFragment)
    }

    @Test
    fun testNavigationToForgotPasswordScreen() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<SignUpFragment>(Bundle(), theme) {
            navController.setGraph(R.navigation.navigation_graph)
            navController.setCurrentDestination(R.id.signUpFragment)
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.buttonNext)).perform(ViewActions.click())
        Assert.assertEquals(navController.currentDestination?.id, R.id.forgotPasswordFragment)
    }

    @Test
    fun testNavigationToLoginScreen() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<ForgotPasswordFragment>(Bundle(), theme) {
            navController.setGraph(R.navigation.navigation_graph)
            navController.setCurrentDestination(R.id.forgotPasswordFragment)
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.buttonNext)).perform(ViewActions.click())
        Assert.assertEquals(navController.currentDestination?.id, R.id.loginFragment)
    }
}
