package com.example.globalsurvey.data.model

data class Poll(
    val id: String = "",
    val question: String = "",
    val options: List<String> = emptyList(),
    val votes: Map<String, Int> = emptyMap(), // optionIndex -> count
    val category: String = "General",
    val createdBy: String = "",
    val createdAt: Long = 0L,
    val totalVotes: Int = 0
)

data class Category(val label: String, val emoji: String)

val categories = listOf(
    Category("All", "🌍"),
    Category("Sports", "🏆"),
    Category("Politics", "🗳️"),
    Category("Entertainment", "🎬"),
    Category("Tech", "💻"),
    Category("Science", "🔬"),
    Category("Food", "🍕"),
    Category("Other", "💬")
)