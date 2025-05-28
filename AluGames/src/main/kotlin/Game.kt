package com.julionborges

data class Game(val title: String, val thumb: String) {
    val description = ""

    override fun toString(): String {
        return """
            Game Details:
            Title: $title
            Thumbnail: $thumb
            Description: $description
        """.trimIndent()
    }
}