package com.numrod.retrofitcomments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.numrod.retrofitcomments.components.Avatar
import com.numrod.retrofitcomments.components.CommentListItem
import com.numrod.retrofitcomments.ui.theme.RetrofitCommentsTheme

@Composable
fun CommentsApp(
    viewModel: CommentsAppViewModel = CommentsAppViewModel()
) {
    val commentsList = viewModel.commentsList.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

//    LaunchedEffect(commentsList) {
//        viewModel.getCommentsList()
//    }
    Scaffold(modifier = Modifier.padding(20.dp)) { paddingVals ->
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

            Button(onClick = {
                viewModel.getCommentsList()
            }) {
                Text(text = "Load Data")
            }

            if (errorMessage.value != null){
                Text(text = errorMessage.value!!)
            }  else if (isLoading.value){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator(
                        modifier = Modifier.size(70.dp)
                    )
                }
            } else{
              LazyColumn {
                  commentsList.value?.let {comments ->
                      items(comments){comment ->
                          CommentListItem(
                              name = comment.name,
                              email = comment.email,
                          )
                      }
                  }
              }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
//    RetrofitCommentsTheme {
//        CommentsApp()
//    }
}