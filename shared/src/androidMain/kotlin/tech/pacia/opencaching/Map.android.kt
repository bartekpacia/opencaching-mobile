package tech.pacia.opencaching

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun Map(
    modifier: Modifier,
    position: Pair<Double, Double>,
    title: String,
) {
    Text("Map not implemented on Android yet")
}
