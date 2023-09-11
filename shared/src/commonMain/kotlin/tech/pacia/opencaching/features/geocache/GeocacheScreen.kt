package tech.pacia.opencaching.features.geocache

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import tech.pacia.opencaching.navigation.NavigationStack
import tech.pacia.opencaching.navigation.Page

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeocacheScreen(navStack: NavigationStack<Page>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Map") },
                navigationIcon = {
                    IconButton(onClick = { navStack.pop() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },

        ) {
        Column {
            Text("Geocache details!")
        }
    }
}
