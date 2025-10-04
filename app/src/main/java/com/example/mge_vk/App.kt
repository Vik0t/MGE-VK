
package com.example.mge_vk



import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mge_vk.ui.theme.MGEVKTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star

@Composable
fun App(
    name: String,
    icon: Painter,
    tag: String,
    stars: Double,
    ageRating: Int,
    navController: NavController
){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().clickable(enabled = true, onClick = {
            navController.navigate("OnboardScreen")})
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

                Row{
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "lal",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        text ="$stars",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.secondary

                    )
                    Spacer(Modifier.width(8.dp))
                    AgeRatingBadge(ageRating = ageRating)
                }


            }

        }
        Button(onClick = {}, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary )  ) {
            Text("Install")
        }
    }
    Spacer(Modifier.height(16.dp))
}


@Composable
fun AgeRatingBadge(ageRating: Int, modifier: Modifier = Modifier)
{
    val ageStr = "$ageRating+"
    Surface(
        color = when (ageStr) {
            "18+" -> Color(0xffe65c53) // Красный для 18+
            "16+" -> Color(0xffd4893d) // Оранжевый для 16+
            "12+" -> Color(0xFF6399cf) // Синий для 12+
            "6+" -> Color(0xFF428245)  // Зеленый для 6+
            else -> Color(0xFF757575)
        },
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
    ) {
        Text(
            text = ageStr,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

