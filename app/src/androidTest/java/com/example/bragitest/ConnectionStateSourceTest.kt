package com.example.bragitest

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bragitest.data.ConnectionStateSource
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConnectionStateSourceTest {

    @Test
    fun testConnectionStateSource() {
        val connectionStateSource = ConnectionStateSource()
        val state1 = connectionStateSource.getNextConnectionState()
        val state2 = connectionStateSource.getNextConnectionState()
        Assert.assertNotNull(state1)
        Assert.assertNotNull(state2)
        Assert.assertNotEquals(state1, state2)
    }
}
