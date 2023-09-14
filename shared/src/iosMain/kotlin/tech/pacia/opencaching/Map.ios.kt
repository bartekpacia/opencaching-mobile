@file:OptIn(ExperimentalForeignApi::class)

package tech.pacia.opencaching

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.datetime.Clock
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.MapKit.MKAnnotationProtocol
import platform.MapKit.MKCoordinateRegionMakeWithDistance
import platform.MapKit.MKMapView
import platform.MapKit.MKMapViewDelegateProtocol
import platform.MapKit.MKPointAnnotation
import platform.darwin.NSObject
import tech.pacia.opencaching.data.BoundingBox
import tech.pacia.opencaching.data.Geocache
import tech.pacia.opencaching.data.Location
import kotlin.time.Duration.Companion.seconds


@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun Map(
    modifier: Modifier,
    center: Location,
    caches: List<Geocache>,
    onMapBoundsChange: (BoundingBox?) -> Unit,
) {
    val annotations = caches.map {
        MKPointAnnotation(
            coordinate = CLLocationCoordinate2DMake(
                it.location.latitude,
                it.location.longitude
            ),
            title = it.name,
            subtitle = it.code,
        )
    }

    val mapViewDelegate = remember {
        MapViewDelegate(
            onMapBoundsChange = onMapBoundsChange,
            // TODO: Move NavStack to CompositionLocal?
            onGeocacheTap = { /* navStack.push(GeocachePage()) */ }
        )
    }

    val mkMapView = remember {
        MKMapView().apply {
            delegate = mapViewDelegate

            setRegion(
                MKCoordinateRegionMakeWithDistance(
                    centerCoordinate = CLLocationCoordinate2DMake(
                        center.latitude,
                        center.longitude
                    ),
                    latitudinalMeters = 10_000.0,
                    longitudinalMeters = 10_000.0
                )
            )

            addAnnotation(
                MKPointAnnotation(CLLocationCoordinate2DMake(center.latitude, center.longitude))
            )

            addAnnotations(annotations)
        }
    }

    LaunchedEffect(annotations.size) {
        mkMapView.addAnnotations(annotations)
    }

    UIKitView(
        modifier = modifier,
        factory = { mkMapView },
        update = { }
    )
}

/**
 * A delegate that fires a callback when map bounds change. It does so in a debounced manner.
 */
class MapViewDelegate(
    private val onMapBoundsChange: (BoundingBox?) -> Unit,
    private val onGeocacheTap: (String) -> Unit
) : NSObject(),
    MKMapViewDelegateProtocol {

    private var lastInstant = Clock.System.now()

    override fun mapView(mapView: MKMapView, didSelectAnnotation: MKAnnotationProtocol) {
        debugLog("MapViewDelegate", "didSelectAnnotation")

        val subtitle = didSelectAnnotation.subtitle
        if (subtitle != null) {
            onGeocacheTap(subtitle)
        }
    }

    override fun mapViewDidChangeVisibleRegion(mapView: MKMapView) {
        val currentInstant = Clock.System.now()
        val duration = currentInstant - lastInstant

        if (duration >= 1.seconds) {
            onMapBoundsChange(mapView.boundingBox())
            lastInstant = currentInstant
        }
    }
}


@OptIn(ExperimentalForeignApi::class)
fun MKMapView.boundingBox(): BoundingBox {
    // Be careful - don't return objects from useContents or dragons will appear

    val latitude = region.useContents { center.latitude }
    val longitude = region.useContents { center.longitude }
    val latitudeDelta = region.useContents { span.latitudeDelta }
    val longitudeDelta = region.useContents { span.longitudeDelta }

    val northWestCorner = CLLocationCoordinate2DMake(
        latitude = latitude + latitudeDelta / 2,
        longitude = longitude - longitudeDelta / 2
    )

    val southEastCorner = CLLocationCoordinate2DMake(
        latitude = latitude - latitudeDelta / 2,
        longitude = longitude + longitudeDelta / 2,
    )

    return BoundingBox(
        north = northWestCorner.useContents { this.latitude },
        east = southEastCorner.useContents { this.longitude },
        south = southEastCorner.useContents { this.latitude },
        west = northWestCorner.useContents { this.longitude },
    )
}
