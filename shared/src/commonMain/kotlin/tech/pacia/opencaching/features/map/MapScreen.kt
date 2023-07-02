package tech.pacia.opencaching.features.map

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import tech.pacia.opencaching.Map
import tech.pacia.opencaching.navigation.NavigationStack
import tech.pacia.opencaching.navigation.Page

@Composable
fun MapScreen(navStack: NavigationStack<Page>) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Map") }) },

        ) {
        Map(
            position = Pair(50.196168, 18.446953),
            title = "xd",
        )
    }
}