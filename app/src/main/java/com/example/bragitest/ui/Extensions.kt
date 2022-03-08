package com.example.bragitest.ui

import android.content.Context
import android.graphics.Color
import com.example.bragitest.R
import com.example.bragitest.data.model.ConnectionState

fun ConnectionState.getConnectionString(context: Context): String = when (this) {
    ConnectionState.CONNECTED -> context.getString(R.string.label_connection_established)
    ConnectionState.CONNECTING -> context.getString(R.string.label_connecting)
    else -> context.getString(R.string.label_connection_error)
}

fun ConnectionState.getConnectionColor(): Int = when (this) {
    ConnectionState.CONNECTED -> Color.GREEN
    ConnectionState.CONNECTING -> Color.parseColor("#FFFF00")
    else -> Color.RED
}
