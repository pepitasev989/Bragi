package com.example.bragitest.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bragitest.data.CommandRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CommandViewModel @Inject constructor(
    private val commandRepository: CommandRepository
) : ViewModel() {

    private var disposableBag: CompositeDisposable = CompositeDisposable()

    fun observeCommandResults() {
        disposableBag.add(commandRepository.getCommandObservable()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.i(
                    "CommandViewModel",
                    "CommandViewModel, commandId = ${it.commandId}, commandName = ${it.commandName}, Finished"
                )
            })
    }

    fun clear() {
        disposableBag.clear()
    }
}
