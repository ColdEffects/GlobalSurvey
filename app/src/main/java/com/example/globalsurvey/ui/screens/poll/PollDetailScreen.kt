package com.example.globalsurvey.ui.screens.poll


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yourname.globalpulse.data.model.Poll
import com.yourname.globalpulse.ui.components.VoteBar

@Composable
fun PollDetailScreen(pollId: String, navController: NavController, paddingValues: PaddingValues) {

    // Placeholder until Firebase — match by ID
    val poll = Poll(
        id = pollId,
        question = "Who is the GOAT of basketball?",
        options = listOf("LeBron James", "Michael Jordan", "Kobe Bryant", "Other"),
        votes = mapOf("0" to 4200, "1" to 5800, "2" to 1200, "3" to 300),
        category = "Sports",
        totalVotes = 11500
    )

    var selectedOption by remember { mutableStateOf<Int?>(null) }
    val hasVoted = selectedOption != null

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        item {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
            ) {
                Text(
                    text = poll.category,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(poll.question, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${poll.totalVotes} votes worldwide",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        if (!hasVoted) {
            item {
                Text("Cast your vote:", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(12.dp))
            }
            items(poll.options.size) { index ->
                OutlinedButton(
                    onClick = { selectedOption = index },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text(poll.options[index])
                }
            }
        } else {
            item {
                Text("Results:", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(12.dp))
                poll.options.forEachIndexed { index, option ->
                    VoteBar(
                        optionText = option,
                        voteCount = poll.votes[index.toString()] ?: 0,
                        totalVotes = poll.totalVotes,
                        isSelected = index == selectedOption
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}