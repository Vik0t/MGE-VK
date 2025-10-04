
package com.example.mge_vk



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mge_vk.ui.theme.MGEVKTheme

@Composable
fun App(
    name: String,
    icon: Painter,
    tag: String,
    stars: Double,
){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = icon,
                contentDescription = name,
                modifier = Modifier.size(70.dp)
            )
            Spacer(Modifier.width(20.dp))
            Column {
                Text(text = name,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary)
                Text(tag)
                Text(
                    text ="$stars rating",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary

                )
            }

        }
        Button(onClick = {}, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary )  ) {
            Text("Install")
        }
    }
    Spacer(Modifier.height(16.dp))
}
