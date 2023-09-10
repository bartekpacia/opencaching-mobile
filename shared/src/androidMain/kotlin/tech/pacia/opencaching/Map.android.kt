package tech.pacia.opencaching

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import tech.pacia.opencaching.data.Geocache

@Composable
actual fun Map(
    modifier: Modifier,
    position: Pair<Double, Double>,
    title: String,
    caches: List<Geocache>,
) {
    Box(
        Modifier.height(100.dp).width(100.dp).background(Color.Green)
    ) {
        Text("MapScreen is not implemented on Android yet")
    }
}
