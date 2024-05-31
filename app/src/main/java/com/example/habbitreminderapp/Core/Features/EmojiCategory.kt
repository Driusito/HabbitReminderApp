package com.example.habbitreminderapp.Core.Features

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
        val emojis = mapOf(
            "Emociones" to listOf("😍", "🥳", "🎉", "👍", "❤️", "😊", "😢", "😡", "😱", "😂"),
            "Deportes" to listOf("🏋️‍♂️", "⚽", "🎾", "🏀", "🏊", "🚴", "🏌️‍♂️", "🏄‍♂️", "🏇", "🤸‍♀️"),
            "Alimentación" to listOf("🍏", "🍔", "🥗", "🍣", "🍕", "🍩", "🍦", "🍓", "🍒", "🍉"),
            "Salud" to listOf("💤", "💧", "🏃", "🧘", "🚶", "🛌", "🧖", "🏥", "💊", "🩺"),
            "Ocio" to listOf("🎮", "🎲", "🎨", "📺", "🎬", "🎤", "🎧", "🎻", "🎷", "🎸"),
            "Animales" to listOf("🐶", "🐱", "🐭", "🐹", "🐰", "🦊", "🐮", "🐷", "🐸", "🐔", "🦉", "🐍", "🐢", "🐠", "🦜"),
            "Otros" to listOf("📚", "🎓", "✈️", "🚀", "🚗", "🏠", "🛏️", "💼", "🛒", "🔧", "💡", "📅", "📌", "🖋️", "🔒")
        )

        val lazyGridState = rememberLazyGridState()
        Dialog(onDismissRequest = { onDismiss() }) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Seleccione un emoji",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.padding(bottom = 16.dp).weight(5f),
                            color = Color.Black
                        )
                        IconButton(onClick = { onDismiss() }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cerrar diálogo",
                                tint = Color.Black, modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(5),
                        state = lazyGridState,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.background(Color(230, 230, 250))
                    ) {
                        emojis.forEach { (category, emojiList) ->
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Text(
                                    text = category,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.Black,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            }
                            items(emojiList) { emoji ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(173, 216, 230, 255))
                                        .clickable {
                                            onEmojiSelected(emoji)
                                            onDismiss()
                                        }
                                        .padding(8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        textAlign = TextAlign.Center,
                                        text = emoji,
                                        fontSize = 24.sp,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
