package com.numrod.retrofitcomments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.numrod.retrofitcomments.ui.theme.RetrofitCommentsTheme

@Composable
fun CommentsApp() {
    Scaffold { paddingVals ->
        Column(
            modifier = Modifier.padding(paddingVals),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Avatar(150.dp)
            Divider(Modifier.height(2.dp))
            Text(text = "Supp Lab")
            Text(text = "Retrofit-API")
            Text(text = "@JetpackCompose")

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Load Data")
            }

        }
    }
}

@Composable
private fun CommentListItem(
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

@Composable
private fun Avatar(
    size: Dp
) {
    Surface(
        modifier = Modifier.size(size),
        shape = CircleShape,

        ) {
        Image(
            painter = painterResource(id = R.drawable.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    RetrofitCommentsTheme {
        CommentsApp()
    }
}