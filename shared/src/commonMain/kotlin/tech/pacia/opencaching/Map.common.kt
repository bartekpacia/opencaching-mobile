package tech.pacia.opencaching

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.pacia.opencaching.data.Geocache

@Composable
expect fun Map(
    modifier: Modifier = Modifier,
    position: Pair<Double, Double>,
    title: String,
    caches: List<Geocache>,
)