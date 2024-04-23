package com.example.habbitreminderapp.Features

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbitreminderapp.Model.data.MenuItem
import com.example.habbitreminderapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onNavigationIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Habbit Reminder App") },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        }
    )
}

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp).clip(CircleShape).background(Color.Red)
        )

    }

}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) }
                    .padding(16.dp)) {
                Icon(imageVector = item.icon, contentDescription = item.contentDescription)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title, style = itemTextStyle, modifier = Modifier.weight(1f))
            }
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Menu() {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet {
                DrawerHeader()
                DrawerBody(
                    items = listOf(
                        MenuItem("0", "Inicio", "Inicio", icon = Icons.Default.Menu),
                        MenuItem("1", "Mi perfil", "Mi perfil", icon = Icons.Default.Face)
                    ),
                    onItemClick = { scope.launch { drawerState.close() } }
                )
            }

        }
    ) {
        Scaffold(
            topBar = {
                AppBar(onNavigationIconClick = { scope.launch { drawerState.open() } })
            },
            content = {
                Text(text = "Contenido principal")
            }
        )
    }
}










