package com.numrod.retrofitcomments.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.numrod.retrofitcomments.R

@Composable
fun Avatar(
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