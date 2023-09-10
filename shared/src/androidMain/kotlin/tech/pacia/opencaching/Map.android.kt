package tech.pacia.opencaching

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import tech.pacia.opencaching.data.Geocache

@Composable
actual fun Map(
    modifier: Modifier,
    latLng: Pair<Double, Double>,
    title: String,
    caches: List<Geocache>,
) {
//    Box(
//        Modifier.height(100.dp).width(100.dp).background(Color.Green)
//    ) {
//        Text("MapScreen is not implemented on Android yet")
//    }

    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = singapore),
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }

}
