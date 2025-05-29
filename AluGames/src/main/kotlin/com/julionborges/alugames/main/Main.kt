package com.julionborges.alugames.main

import com.julionborges.alugames.model.Game
import com.julionborges.alugames.service.ApiConsumer
import java.util.Scanner

fun main() {
    val reader = Scanner(System.`in`)

    println("Enter the game ID:")
    val gameId = reader.nextLine()

    val myGameInfo = ApiConsumer().findGame(gameId)
    var myGame: Game? = null

    val result = runCatching {
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