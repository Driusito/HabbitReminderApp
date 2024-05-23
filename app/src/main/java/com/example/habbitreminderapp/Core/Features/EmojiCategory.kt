package com.example.habbitreminderapp.Core.Features

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun EmojiSelector(onEmojiSelected: (String) -> Unit) {
    val emojis = listOf("😀", "😂", "😊", "😍", "🥳", "🎉", "👍", "❤️") // Lista de emojis disponibles
    val state = rememberLazyGridState()
    LazyVerticalGrid(state=state,
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        items(emojis) { emoji ->
            Text(
                text = emoji,
                fontSize = 24.sp,
                modifier = Modifier
                    .clickable {
                        onEmojiSelected(emoji)
                    }
                    .padding(8.dp)
            )
        }
        //item { Icon(imageVector = Icons.Default.AddCircle, contentDescription ="" ) }
    }
}

@Preview
@Composable
fun showEmojiSelector() {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        EmojiSelector(onEmojiSelected = {})

    }
}