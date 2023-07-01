package tech.pacia.opencaching

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform