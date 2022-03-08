package com.example.bragitest.ui.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.example.bragitest.data.ConnectionStateRepository
import com.example.bragitest.data.model.ConnectionState
import com.example.bragitest.ui.getConnectionColor
import com.example.bragitest.ui.getConnectionString
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ConnectionStateViewModel @Inject constructor(
    application: Application,
    private val connectionStateRepository: ConnectionStateRepository
) : AndroidViewModel(application) {

    private lateinit var disposableBag: CompositeDisposable

    var connectionState = ConnectionState.ERROR
    val connectionStateString = ObservableField<String>("")
    val connectionTextColor = ObservableField<Int>(0)

    fun observeData() {
        disposableBag = CompositeDisposable()
        disposableBag.add(connectionStateRepository.getConnectionStateObservable()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                connectionState = it
                connectionStateString.set(connectionState.getConnectionString(getApplication<Application>().applicationContext))
                connectionTextColor.set(connectionState.getConnectionColor())
            })
    }

    fun clear() {
        disposableBag.clear()
    }
}
