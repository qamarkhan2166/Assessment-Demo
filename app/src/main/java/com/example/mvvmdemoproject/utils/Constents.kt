package com.example.mvvmdemoproject.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

fun showSnack(message: String, view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun isNetworkAvailable(): Boolean {
    return try {
        val timeoutMs = 2000
        val sock = Socket()
        val socketAddress: SocketAddress = InetSocketAddress("8.8.8.8", 53)
        sock.connect(socketAddress, timeoutMs)
        sock.close()
        true
    } catch (e: Exception) {
        false
    }
}
