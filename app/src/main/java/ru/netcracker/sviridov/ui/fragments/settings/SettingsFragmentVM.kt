package ru.netcracker.sviridov.ui.fragments.settings

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import ru.netcracker.sviridov.App
import ru.netcracker.sviridov.api.model.CityResponse
import java.util.concurrent.TimeUnit

/**
 * Created by Turkin A. on 13/11/2018.
 */
class SettingsFragmentVM : ViewModel() {

    private val autoCompletePublishSubject = PublishRelay.create<String>()

    private val cities = MutableLiveData<ArrayList<String>>()

    val suggestions = MutableLiveData<List<CityResponse>>()
    val suggestionTitles = MutableLiveData<List<String>>()

    init {
        configureAutocompleteSearch()
    }

    @SuppressLint("CheckResult")
    private fun configureAutocompleteSearch() {
        autoCompletePublishSubject
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter { text -> !text.isEmpty() }
            .distinctUntilChanged()
            .switchMap { App.api().getCities(it).onErrorResumeNext(Observable.empty()) }
            .map {
                suggestions.postValue(it)
                val citiesNames = ArrayList<String>()
                for (city in it) {
                    citiesNames.add(city.title ?: "")
                }
                citiesNames
            }
            .subscribeOn(Schedulers.io())
            .subscribe({
                suggestionTitles.postValue(it)
            }, {
                it.printStackTrace()
            })
    }

    fun onSearchStateChanged(query: String) {
        autoCompletePublishSubject.accept(query.trim())
    }

    /*
    * In case of method in init block will now work
    * */
    @SuppressLint("CheckResult")
    fun getCities(query: String): MutableLiveData<ArrayList<String>> {
        App
            .api()
            .getCities(query)
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter { !query.isEmpty() }
            .distinctUntilChanged()
            .map {
                val citiesNames = ArrayList<String>()
                for (city in it) {
                    citiesNames.add(city.title ?: "")
                }
                citiesNames
            }
            .subscribeOn(Schedulers.io())
            .subscribe({
                cities.postValue(it)
            }, {
                it.printStackTrace()
            })

        return cities
    }

    companion object {
        const val TAG = "SettingsFragmentVM"
    }

}