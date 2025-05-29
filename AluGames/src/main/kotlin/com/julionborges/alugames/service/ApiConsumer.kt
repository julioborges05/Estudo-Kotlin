package com.julionborges.alugames.service

import com.google.gson.Gson
import com.julionborges.alugames.model.Game
import com.julionborges.alugames.model.GameInfo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*

class ApiConsumer {

    fun findGame(id: String): GameInfo {

        val address = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(address))
            .build()
        val response = client.send(request, BodyHandlers.ofString())

        var myGameInfo: GameInfo? = null

        val result = runCatching {
            val gson = Gson()
            myGameInfo = gson.fromJson(response.body(), GameInfo::class.java)
        }

        if (result.isFailure || myGameInfo == null) {
            throw Exception("Error: invalid id or no game found.")
        }

        return myGameInfo as GameInfo
    }


}