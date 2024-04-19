package com.numrod.businesscard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.numrod.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    BusinessCard()

                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    var showProjects by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .size(290.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            ProfileImage()
            ProfileContent()

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                Log.d("Clicked", "Show Portfolio Clicked")
                showProjects = !showProjects
            }) {
                Text(text = if (showProjects) "Hide Portfolio" else "Show Portfolio")
            }

            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Spacer(modifier = Modifier.height(10.dp))

            if (showProjects) PortfolioList()
        }
    }
}

@Composable
fun PortfolioList(
    projects: List<String> = listOf("rhapsodyArts", "Pop Up")
) {

    LazyColumn(
        modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(projects) { project ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(10.dp),

                ) {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = project,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun ProfileContent() {
    Text(text = "Profiliu I.M Nandjebo", style = MaterialTheme.typography.displaySmall)
    Text(
        text = "Software Engineer", fontSize = 20.sp, style = MaterialTheme.typography.titleMedium
    )
    Text(text = "@rhapsodic_numrod", style = MaterialTheme.typography.titleMedium)
}

@Composable
fun ProfileImage() {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(7.dp)
            .fillMaxSize(),
        shape = CircleShape,

        ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile picture",
            contentScale = ContentScale.Crop,

            )
    }
}


@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}