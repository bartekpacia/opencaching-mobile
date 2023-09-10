package tech.pacia.opencaching.data

import kotlinx.serialization.Serializable

@Serializable
data class PoorMansGeocache(
    val code: String,
    val name: String,
    val location: String,
    val status: String,
    val type: String,
)

@Serializable
data class Geocache(
    val code: String,
    val name: String,
    val location: Location,
    val type: Type,
    val status: Status,
    val url: String,
    val owner: String,
) {
    enum class Type { TRADITIONAL, MULTI, QUIZ }

    enum class Status { AVAILABLE, TEMPORARILY_UNAVAILABLE, ARCHIVED }
}

@Serializable
data class Location(
    val latitude: Double,
    val longitude: Double,
)

data class BoundingBox(
    val north: Location,
    val east: Location,
    val south: Location,
    val west: Location,
) {
    fun toPipeFormat() = "$north|$east|$south|$west"
}