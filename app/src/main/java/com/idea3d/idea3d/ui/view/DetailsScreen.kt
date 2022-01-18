package com.idea3d.idea3d.ui.view

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import com.idea3d.idea3d.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.idea3d.idea3d.ui.theme.Idea3DTheme
import com.idea3d.idea3d.ui.viewModel.DetailsScreenViewModel

@Composable
fun DetailsScreen(
    newTitle: String,
    navController: NavController,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val new by viewModel.getNewsByTitle(newTitle).observeAsState(initial = null)
    DetailsScreen(newTitle, navController, new)
}

@Composable
fun DetailsScreen(
    newTitle: String,
    navController: NavController,
    new: News?,
) {
    Scaffold() {

        Column(modifier=Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally ) {


            Image(
                painterResource(id = R.drawable.logo_largo_dos),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 22.dp, vertical = 6.dp)
                    .size(165.dp, 31.dp)
            )
            Spacer(modifier  = Modifier.padding(5.dp))
        new?.let {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                Column {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f),
                        painter = rememberImagePainter(
                            data = new.urlToImage,
                            builder = {
                                placeholder(R.drawable.placeholder)
                                error(R.drawable.placeholder)
                            }
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                    Column(Modifier.padding(8.dp)) {
                        val context = LocalContext.current

                        Text(new.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(new.content ?: "",
                        modifier=Modifier.padding(0.dp, 8.dp))
                        Box(Modifier.size(8.dp))
                        Button(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),

                            onClick = {
                                val intent =
                                    Intent(Intent.ACTION_VIEW, android.net.Uri.parse(new.url))
                                context.startActivity(intent)
                            }) {
                            Text("Ver más...")
                        }
                    }
                }
            }

            }
        } ?: run {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    Idea3DTheme {
        DetailsScreen(
            newTitle = "Hello",
            navController = rememberNavController(),
            new = News(
                "Title", "Content description", "", "",
                "https://via.placeholder.com/540x300?text=yayocode.com"
            )
        )
    }
}