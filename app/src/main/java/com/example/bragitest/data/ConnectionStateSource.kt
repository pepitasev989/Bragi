package com.example.bragitest.data

import com.example.bragitest.data.model.ConnectionState
import kotlin.random.Random

class ConnectionStateSource {

    private var connectionStateInt = -1

    init {
        generateNextConnectionState()
    }

    fun getNextConnectionState(): ConnectionState {
        return generateNextConnectionState()
    }

    private fun generateNextConnectionState(): ConnectionState {
        var randomInt = Random.nextInt(0, 3)
        while (connectionStateInt == randomInt) {
            randomInt = Random.nextInt(0, 3)
        }
        connectionStateInt = randomInt
        return getConnectionStateForInt(connectionStateInt)
    }

    private fun getConnectionStateForInt(intValue: Int) = when (intValue) {
        0 -> ConnectionState.CONNECTED
        1 -> ConnectionState.CONNECTING
        2 -> ConnectionState.ERROR
        else -> ConnectionState.ERROR
    }
}
