package ru.netcracker.sviridov.ui.fragments.weather

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.netcracker.sviridov.App
import ru.netcracker.sviridov.api.model.WeatherResponse
import ru.netcracker.sviridov.utils.exts.getCity
import java.util.concurrent.TimeUnit

/**
 * Created by Turkin A. on 13/11/2018.
 */
class WeatherFragmentVM(application: Application) : AndroidViewModel(application) {

    val loading = MutableLiveData<Boolean>()
    val weatherResponse = MutableLiveData<WeatherResponse>()

    private val compositeDisposableOrdinal = CompositeDisposable()
    private val globalCompositeDisposable = CompositeDisposable()

    init {
        longPollingRx()
    }

    @SuppressLint("CheckResult")
    fun getWeather(woeid: Long) {
        compositeDisposableOrdinal.add(
            App
                .api()
                .getWeather(woeid)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { loading.postValue(true) }
                .doFinally {
                    loading.postValue(false)
                    compositeDisposableOrdinal.clear()
                }
                .subscribe({
                    weatherResponse.postValue(it)
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun longPollingRx() {
        globalCompositeDisposable
            .add(
                Observable
                    .interval(10, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .forEach {
                        val woeid = getApplication<App>().getCity()
                        App
                            .api()
                            .getWeather(woeid)
                            .subscribe({
                                weatherResponse.postValue(it)
                            }, {
                                it.printStackTrace()
                            })
                    }
            )
    }

    override fun onCleared() {
        globalCompositeDisposable.clear()
        compositeDisposableOrdinal.clear()
        super.onCleared()
    }

    companion object {
        const val TAG = "WeatherFragmentVM"
    }

}