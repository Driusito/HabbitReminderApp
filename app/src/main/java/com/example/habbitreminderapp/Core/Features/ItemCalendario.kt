package com.example.habbitreminderapp.Core.Features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ItemCalendario(nombreTarea:String,horaTarea:String){

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Cyan), verticalAlignment = Alignment.CenterVertically){
        Text(text = nombreTarea, modifier = Modifier.weight(3f))
        Text(text = horaTarea, Modifier.weight(2f))
        BotonConfirmar {

        }
    }

}