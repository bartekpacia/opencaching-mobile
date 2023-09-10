@file:OptIn(ExperimentalForeignApi::class)

package tech.pacia.opencaching

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.MapKit.MKCoordinateRegionMakeWithDistance
import platform.MapKit.MKMapView
import platform.MapKit.MKPointAnnotation
import tech.pacia.opencaching.data.BoundingBox
import tech.pacia.opencaching.data.Geocache

@Composable
actual fun Map(
    modifier: Modifier,
    latLng: Pair<Double, Double>,
    caches: List<Geocache>,
    onMapBoundsChange: (BoundingBox?) -> Unit,
) {
    val coordinate = CLLocationCoordinate2DMake(latLng.first, latLng.second)
    val annotation = remember {
        MKPointAnnotation(
            coordinate = coordinate,
            title = "hehe",
            subtitle = null,
        )
    }

    val mkMapView = remember { MKMapView().apply { addAnnotation(annotation) } }

    UIKitView(
        modifier = modifier,
        factory = { mkMapView },
        update = {
            mkMapView.setRegion(
                MKCoordinateRegionMakeWithDistance(
                    centerCoordinate = coordinate,
                    latitudinalMeters = 10_000.0,
                    longitudinalMeters = 10_000.0
                ),
                animated = false,
            )
        }
    )
}