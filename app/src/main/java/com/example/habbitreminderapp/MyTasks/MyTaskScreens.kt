package com.example.habbitreminderapp.MyTasks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.ViewList
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.habbitreminderapp.MyTasks.MyTaskCalendar.ui.MyCalendarFlat
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTable
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTaskScreens(myTaskTableViewModel: MyTaskTableViewModel) {
    val tabItems = listOf(
        TabItem(
            title = "Lista",
            selectedIcon = Icons.AutoMirrored.Filled.List
        ),
        TabItem(
            title = "Calendario",
            selectedIcon = Icons.Default.CalendarMonth
        )
    )
    val pantallas = listOf(
        MyTaskTable(), MyCalendarFlat()
    )
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { pantallas.size }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }

    Column(Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = (index == selectedTabIndex),
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = item.title) },
                    icon = {
                        Icon(
                            imageVector = item.selectedIcon,
                            contentDescription = ""
                        )
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            when (page) {
                //Cambiar aqui las pantallas
                0 -> MyTaskTable()
                1 -> MyCalendarFlat()
                else -> throw IllegalStateException("Invalid page index")
            }
        }
    }
}

data class TabItem(val title: String, val selectedIcon: ImageVector)