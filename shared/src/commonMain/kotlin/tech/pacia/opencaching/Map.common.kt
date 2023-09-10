package tech.pacia.opencaching

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.pacia.opencaching.data.BoundingBox
import tech.pacia.opencaching.data.Geocache

@Composable
expect fun Map(
    modifier: Modifier = Modifier,
    latLng: Pair<Double, Double>,
    caches: List<Geocache>,
    onMapBoundsChange: (BoundingBox?) -> Unit,
)