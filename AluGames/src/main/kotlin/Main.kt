package com.julionborges

import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner

fun main() {
    val reader = Scanner(System.`in`)
    println("Enter the game ID:")
    val gameId = reader.nextLine()

    val address = "https://www.cheapshark.com/api/1.0/games?id=$gameId"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(address))
        .build()
    val response = client.send(request, BodyHandlers.ofString())

    var myGame: Game? = null

    val result = runCatching {
        val gson = Gson()
        val myGameInfo = gson.fromJson(response.body(), GameInfo::class.java)

        myGame = Game(myGameInfo.info.title, myGameInfo.info.thumb)
    }

    result.onFailure {
        println("Error: invalid id or no game found.")
    }

    result.onSuccess {
        println("Do you want to add to add a game description? (Y/N)")
        val option = reader.nextLine()

        if (option.equals("Y", true)) {
            println("Enter the game description:")
            val customDescription = reader.nextLine()
            myGame?.description = customDescription
        } else {
            myGame?.description = myGame?.title
        }

        println(myGame)
    }

    result.onSuccess {
        println("Application closed successfully.")
    }


}