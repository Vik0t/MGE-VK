
package com.example.mge_vk



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun App(
    name: String,
    icon: ImageVector,
    tag: String,
    stars: Double,
){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        Row(

        ) {
            Icon(
                imageVector = icon,
                contentDescription = name,
                modifier = Modifier.size(64.dp)
            )
            Column {
                Text(name, color = MaterialTheme.colorScheme.primary)
                Text(tag)
                Text(
                    text ="$stars rating")
            }

        }
        Button(onClick = {}) {
            Text("Install")
        }
    }
}