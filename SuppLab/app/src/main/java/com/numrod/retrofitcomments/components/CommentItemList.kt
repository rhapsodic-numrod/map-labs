package com.numrod.retrofitcomments.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CommentListItem(
    email: String,
    name: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.8F)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Avatar(size = 65.dp)

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = email, fontWeight = FontWeight.Bold)
            Text(text = name)
        }
    }
}