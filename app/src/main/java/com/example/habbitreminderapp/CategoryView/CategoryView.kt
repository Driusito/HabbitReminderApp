package com.example.habbitreminderapp.CategoryView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Blender
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlin.random.Random

@Preview
@Composable
fun mostrarCategorias() {
    var state = rememberLazyGridState()
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(columns = GridCells.Fixed(2), state = state, content = {
                items(10) {
                    itemCategoria()

                }
            })
        }

    }
}


@Composable
fun itemCategoria() {
    val interactionSource = remember { MutableInteractionSource() }
    val draggable = Modifier.draggable(
        interactionSource = interactionSource,
        orientation = Orientation.Horizontal,
        state = rememberDraggableState { /* update some business state here */ }
    )
    val clickable = Modifier.clickable(
        interactionSource = interactionSource,
        indication = LocalIndication.current
    ) { /* update some business state here */ }
    val isDragged by interactionSource.collectIsDraggedAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    val (text, color) = when {
        isDragged && isPressed -> "Dragged and pressed" to Color.Red
        isDragged -> "Dragged" to Color.Green
        isPressed -> "Pressed" to Color.Blue
        // Default / baseline state
        else -> "Drag me horizontally, or press me!" to Color.Black
    }
    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize()
            .size(width = 240.dp, height = 80.dp)
    ) {
        Box(
            Modifier
                .padding(8.dp)
                .shadow(10.dp).clip(AbsoluteCutCornerShape(8.dp))
                .fillMaxSize()
                .background(Color.Red)

                .then(clickable)
                .then(draggable)


        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text,
                    style = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(5.dp)
                        .wrapContentSize()
                        .weight(.5f)
                )
                Spacer(Modifier.weight(.1f))
                Icon(imageVector = Icons.Default.Blender, contentDescription = "", modifier = Modifier.weight(.5f))
            }
        }
    }
}