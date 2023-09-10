package tech.pacia.opencaching.features.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import tech.pacia.opencaching.Map
import tech.pacia.opencaching.data.CachesRepository
import tech.pacia.opencaching.data.Geocache
import tech.pacia.opencaching.navigation.NavigationStack
import tech.pacia.opencaching.navigation.Page

@Composable
fun MapScreen(navStack: NavigationStack<Page>) {
    val position = Pair(50.196168, 18.446953)

    val scope = rememberCoroutineScope()
    var geocache by remember { mutableStateOf<Geocache?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Map") }) },

        ) {
        Column {
            Map(
                Modifier.padding(8.dp),
                latLng = position,
                title = "xd",
                caches = listOf()
            )

            Box(Modifier.height(50.dp).width(200.dp).background(Color.Red))

            LaunchedEffect(position) {
                scope.launch {
                    val client = HttpClient {
                        install(ContentNegotiation) {
                            json(Json {
                                ignoreUnknownKeys = true
                            })
                        }
                    }
                    val repository = CachesRepository(client)

                    delay(1000)

                    try {
                        geocache = repository.getGeocache("OP9655")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            geocache?.let {
                Text("Name: ${it.name}")
                Text("Type: ${it.type}")
                Text("Owner username: ${it.owner.username}")
            }
        }
    }
}
