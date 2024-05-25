package com.example.habbitreminderapp.Core.Features

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun EmojiSelector(
    mostrar: Boolean,
    onEmojiSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    if (mostrar) {
        val emojis = listOf(
            " ",
            "😍", // Amor
            "🥳", // Celebración
            "🎉", // Fiesta
            "👍", // Aprobación
            "❤️", // Corazón (Amor)

            "🏋️‍♂️", // Levantamiento de pesas (Deporte)
            "⚽", // Fútbol (Deporte)
            "🎾", // Tenis (Deporte)
            "🏀", // Baloncesto (Deporte)

            "🍏", // Manzana (Alimentación)
            "🍔", // Hamburguesa (Alimentación)
            "🥗", // Ensalada (Alimentación)
            "🍣", // Sushi (Alimentación)
            "🍕", // Pizza (Alimentación)

            "💤", // Dormir (Salud)
            "💧", // Agua (Salud)
            "🏃", // Correr (Salud)

            "📚", // Libros

            "🎮", // Videojuegos (Ocio)
            "🎲", // Juegos de mesa (Ocio)
            "🎨", // Arte (Ocio)
            "📺", // Ver televisión (Ocio)
            "🎬", // Películas (Ocio)

            "🐶", // Perro
            "🐱", // Gato
            "🐭", // Ratón
            "🐹", // Hámster
            "🐰", // Conejo
            "🦊", // Zorro
            "🐮", // Vaca
            "🐷", // Cerdo
            "🐸", // Rana
            "🐔", // Gallina
            "🦉", // Búho
            "🐍", // Serpiente
            "🐢", // Tortuga
            "🐠", // Pez
            "🦜", // Loro
        ) // Lista de emojis disponibles

        Dialog(onDismissRequest = { onDismiss() }) {
            LazyVerticalGrid(modifier = Modifier.background(Color(50, 173, 221, 255)),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                items(emojis) { emoji ->
                    Text(textAlign = TextAlign.Center,
                        text = emoji,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .clickable {
                                onEmojiSelected(emoji)
                                onDismiss()
                            }
                            .padding(8.dp).clip(RoundedCornerShape(20.dp)).background(Color.White)
                    )
                }
            }
        }
    }
}



