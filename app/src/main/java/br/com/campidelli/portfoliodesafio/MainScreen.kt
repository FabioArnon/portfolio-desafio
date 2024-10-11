package br.com.campidelli.portfoliodesafio

import NewsListScreen
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    val tabs = listOf("G1", "Agronegócio")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index }
                    )
                }
            }
        }
    ) { paddingValues ->
        when (selectedTabIndex) {
            0 -> NewsListScreen(navController = navController, uri = "g1", modifier = Modifier.padding(paddingValues))
            1 -> NewsListScreen(navController = navController, uri = "https://g1.globo.com/economia/agronegocios", modifier = Modifier.padding(paddingValues))
        }
    }
}