package com.idea3d.idea3d.ui.view




import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter


import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.idea3d.idea3d.data.model.Info
import com.idea3d.idea3d.data.model.infoModel
import com.idea3d.idea3d.data.model.infoModel.Companion.recInfo

val rnds = (0..10).random()

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            RecycleV(recInfo = recInfo)
        }
    }


@Composable
fun RecycleCard(info: Info) {


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
                
                Text(text= info.titulo,
                    fontSize = 18.sp, fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier=Modifier.padding(vertical= 5.dp))
                Text(text= info.descripcion,
                    fontSize = 12.sp
                    ,modifier=Modifier
                        .size(220.dp)
                    , textAlign = TextAlign.Start)


       }
    }

}


@Composable
fun RecycleV (recInfo: List<Info>){

   Column(modifier=Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally ) {

       Image(painterResource(id = R.drawable.logo_largo_dos),
           contentDescription = null,
           modifier = Modifier
               .padding(horizontal = 22.dp, vertical = 6.dp)
               .size(165.dp, 31.dp))

       Text(text= "Materiales", fontWeight = FontWeight.Black,
            color= Color.Gray,
            fontSize = 26.sp,
           modifier = Modifier
               .padding(8.dp)
            /*style = MaterialTheme.typography.h4.copy(
            shadow= Shadow(
                offset = Offset(5f, 5f),
                blurRadius = 5f,
                color=Color.Gray.copy(alpha=0.2f)
            )),*/


        )
        Spacer(modifier  = Modifier.padding(48.dp))
        LazyRow(verticalAlignment = Alignment.CenterVertically ,

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




