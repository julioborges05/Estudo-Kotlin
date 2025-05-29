package com.julionborges.alugames.model

data class GameInfo(val info: InfoApiShark) {

    override fun toString(): String {
        return info.toString()
    }
}