package com.idea3d.idea3d.ui.view




import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image


import com.idea3d.idea3d.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.idea3d.idea3d.data.model.Info
import com.idea3d.idea3d.data.model.infoModel.Companion.recInfo
import org.intellij.lang.annotations.JdkConstants


class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            RecycleV(recInfo = recInfo)
        }
    }


@Composable
fun RecycleCard(info: Info) {
    //@DrawableRes val image= R.drawable.bati

    Surface(shape= RoundedCornerShape (8.dp), elevation = 8.dp, modifier = Modifier.padding(8.dp)){
       Column(modifier=Modifier.padding(16.dp),
       horizontalAlignment = Alignment.CenterHorizontally ){
                val imageModifier= Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .size(164.dp)
                    .fillMaxSize()


                Image(
                    painterResource(info.imagenRes),
                    contentDescription = "imagen",
                    modifier=imageModifier,
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text= info.titulo,
                    fontSize = 18.sp, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start )
                Text(text= info.descripcion, modifier=Modifier.size(220.dp), textAlign = TextAlign.Start)


       }
    }

}


@Composable
fun RecycleV (recInfo: List<Info>){

    Column(modifier=Modifier.padding(16.dp)) {

        Image(
            painterResource(R.drawable.torre),
            contentDescription = "imagen",
            modifier=Modifier.size(150.dp).fillMaxWidth()

            )
        Spacer(modifier = Modifier.padding(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        )
        {
            items(recInfo) { item ->
                RecycleCard(info = item)
            }


        }
    }
}


@Preview
@Composable
fun RecyclePreview(){
    RecycleCard(info = recInfo[0])
}


}




