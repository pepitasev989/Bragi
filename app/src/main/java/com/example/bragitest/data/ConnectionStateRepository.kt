package com.example.bragitest.data

import com.example.bragitest.data.model.ConnectionState
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ConnectionStateRepository @Inject constructor(private val connectionStateSource: ConnectionStateSource) {

    fun getConnectionStateObservable(): Observable<ConnectionState> {
        return Observable.interval(0, 5, TimeUnit.SECONDS).flatMap {
            return@flatMap Observable.create<ConnectionState> {
                it.onNext(connectionStateSource.getNextConnectionState())
            }
        }
    }
}
