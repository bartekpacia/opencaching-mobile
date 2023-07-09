package tech.pacia.opencaching.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType.Application.Json

const val API_URL = "https://opencaching.pl/okapi/services"
const val CONSUMER_KEY = "duM7DuHSXQtLK7PCx9ee"

class CachesRepository(private val client: HttpClient) {

//    suspend fun searchInBoundingBox(bbox: BoundingBox): List<Geocache> {
//        client.get("$API_URL/caches/search/bbox").body<>()
//    }

    suspend fun getGeocache(code: String): PoorMansGeocache {
        val response = client.get("$API_URL/caches/geocache") {
            accept(Json)
            parameter("consumer_key", CONSUMER_KEY)
            parameter("cache_code", code)
            parameter("fields", "code|name|location|type|status|url|owner")
        }

        print("response: $response")

        return response.body()
    }
}