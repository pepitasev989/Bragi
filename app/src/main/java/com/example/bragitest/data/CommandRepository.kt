package com.example.bragitest.data

import com.example.bragitest.data.model.Command
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class CommandRepository {

    fun getCommandObservable(): Observable<Command> {
        return Observable.range(1, 10).concatMap {
            Observable.just(Command(it, "Command$it")).delay(it.toLong(), TimeUnit.SECONDS)
        }
    }
}
