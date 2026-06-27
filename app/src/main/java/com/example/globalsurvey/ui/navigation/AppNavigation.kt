package com.example.globalsurvey.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourname.globalpulse.ui.screens.home.HomeScreen
import com.yourname.globalpulse.ui.screens.create.CreatePollScreen
import com.yourname.globalpulse.ui.screens.profile.ProfileScreen
import com.yourname.globalpulse.ui.screens.poll.PollDetailScreen
import com.yourname.globalpulse.ui.components.BottomNavBar
import androidx.compose.material3.Scaffold

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen(navController = navController, paddingValues = padding)
            }
            composable("create") {
                CreatePollScreen(navController = navController, paddingValues = padding)
            }
            composable("profile") {
                ProfileScreen(paddingValues = padding)
            }
            composable("poll/{pollId}") { backStackEntry ->
                val pollId = backStackEntry.arguments?.getString("pollId") ?: ""
                PollDetailScreen(pollId = pollId, navController = navController, paddingValues = padding)
            }
        }
    }
}