package com.example.globalsurvey.ui.screens.create


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yourname.globalpulse.data.model.categories

@Composable
fun CreatePollScreen(navController: NavController, paddingValues: PaddingValues) {
    var question by remember { mutableStateOf("") }
    var options by remember { mutableStateOf(mutableListOf("", "")) }
    var selectedCategory by remember { mutableStateOf("General") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        item {
            Text("Create a Poll", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            OutlinedTextField(
                value = question,
                onValueChange = { question = it },
                label = { Text("Your question") },
                placeholder = { Text("e.g. Who is the GOAT of basketball?") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = false,
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Text("Options", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(options.size) { index ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = options[index],
                    onValueChange = { newVal ->
                        options = options.toMutableList().also { it[index] = newVal }
                    },
                    label = { Text("Option ${index + 1}") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                if (options.size > 2) {
                    IconButton(onClick = {
                        options = options.toMutableList().also { it.removeAt(index) }
                    }) {
                        Icon(Icons.Default.Close, contentDescription = "Remove")
                    }
                }
            }
        }

        item {
            if (options.size < 6) {
                TextButton(
                    onClick = { options = options.toMutableList().also { it.add("") } },
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Add option")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Text("Category", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            val chipCategories = categories.filter { it.label != "All" }
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                chipCategories.forEach { category ->
                    FilterChip(
                        selected = selectedCategory == category.label,
                        onClick = { selectedCategory = category.label },
                        label = { Text("${category.emoji} ${category.label}") }
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
        }

        item {
            Button(
                onClick = { /* will wire to Firebase later */ },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                enabled = question.isNotBlank() && options.count { it.isNotBlank() } >= 2
            ) {
                Text("Publish Poll 🌍", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}