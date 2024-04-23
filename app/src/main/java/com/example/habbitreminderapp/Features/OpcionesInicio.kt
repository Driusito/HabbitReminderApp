package com.example.habbitreminderapp.Features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MyOpcion(icon:ImageVector,nombre:String){
    Box(modifier = Modifier.fillMaxWidth()){
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon , contentDescription ="Inicio", tint = Color.Black, modifier = Modifier.padding(15.dp) )
            Text(text = nombre, modifier = Modifier.padding(15.dp))

        }
    }
}