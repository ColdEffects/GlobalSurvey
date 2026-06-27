package com.example.globalsurvey.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourname.globalpulse.data.model.Category

@Composable
fun CategoryChip(
    category: Category,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = "${category.emoji} ${category.label}",
                style = MaterialTheme.typography.labelSmall
            )
        },
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(end = 8.dp)
    )
}