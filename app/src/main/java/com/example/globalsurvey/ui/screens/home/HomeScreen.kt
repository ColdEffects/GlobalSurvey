package com.example.globalsurvey.ui.screens.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yourname.globalpulse.data.model.Poll
import com.yourname.globalpulse.data.model.categories
import com.yourname.globalpulse.ui.components.CategoryChip
import com.yourname.globalpulse.ui.components.PollCard

@Composable
fun HomeScreen(navController: NavController, paddingValues: PaddingValues) {
    var selectedCategory by remember { mutableStateOf("All") }

    // Placeholder polls until Firebase is connected
    val mockPolls = listOf(
        Poll(
            id = "1",
            question = "Who is the GOAT of basketball?",
            options = listOf("LeBron James", "Michael Jordan", "Kobe Bryant", "Other"),
            votes = mapOf("0" to 4200, "1" to 5800, "2" to 1200, "3" to 300),
            category = "Sports",
            totalVotes = 11500
        ),
        Poll(
            id = "2",
            question = "Best programming language in 2025?",
            options = listOf("Python", "Kotlin", "TypeScript", "Rust"),
            votes = mapOf("0" to 3100, "1" to 900, "2" to 2200, "3" to 800),
            category = "Tech",
            totalVotes = 7000
        ),
        Poll(
            id = "3",
            question = "Pineapple on pizza — yes or no?",
            options = listOf("Yes, obviously", "Absolutely not"),
            votes = mapOf("0" to 2100, "1" to 6400),
            category = "Food",
            totalVotes = 8500
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Global Pulse 🌍",
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "What does the world think?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }

        item {
            LazyRow(
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            ) {
                items(categories) { category ->
                    CategoryChip(
                        category = category,
                        selected = selectedCategory == category.label,
                        onClick = { selectedCategory = category.label }
                    )
                }
            }
        }

        val filtered = if (selectedCategory == "All") mockPolls
        else mockPolls.filter { it.category == selectedCategory }

        items(filtered) { poll ->
            PollCard(poll = poll) {
                navController.navigate("poll/${poll.id}")
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}