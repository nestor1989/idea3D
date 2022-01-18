package com.idea3d.idea3d.ui.view



import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


import coil.compose.rememberImagePainter
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.ui.view.ui.theme.Idea3DTheme
import com.idea3d.idea3d.ui.viewModel.ListScreenViewModel

import com.idea3d.idea3d.R


@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListScreenViewModel = hiltViewModel()
) {
    val newsList by viewModel.getNews().observeAsState(initial = emptyList())
    ListScreenTo(navController, newsList)

}



@Composable
fun ListScreenTo(
    navController: NavController,
    news: List<News>
) {
    Scaffold()
    {

        Column(modifier=Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally ) {


            Image(
                painterResource(id = R.drawable.logo_largo_dos),
                contentDescription = null,
                alignment=Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 22.dp, vertical = 6.dp)
                    .size(165.dp, 31.dp)

            )
            Spacer(modifier  = Modifier.padding(5.dp))
            LazyColumn {
                items(news) { new ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("${Destinations.DETAILS_SCREEN}/${new.title}")
                            }
                    ) {
                        Column {

                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(16f / 9f),

                                painter = rememberImagePainter(
                                    data = new.urlToImage,
                                    builder = {
                                        placeholder(R.drawable.logoidea)
                                        error(R.drawable.bati)
                                    }
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth
                            )
                            Column(Modifier.padding(8.dp)) {
                                Text(new.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Text(new.content ?: "", maxLines = 3)
                            }
                        }

                    }
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    Idea3DTheme (
        darkTheme = false
            ) {
        ListScreenTo(
            navController = rememberNavController(),
            news = arrayListOf(
                News(
                    "Title", "Content description", "", "",
                    "https://via.placeholder.com/540x300?text=yayocode.com"
                ),
                News(
                    "Title2", "Content description", "", "",
                    "https://via.placeholder.com/540x300?text=yayocode.com"
                ),
                News(
                    "Title3", "Content description", "", "",
                    "https://via.placeholder.com/540x300?text=yayocode.com"
                )
            )
        )
    }
}