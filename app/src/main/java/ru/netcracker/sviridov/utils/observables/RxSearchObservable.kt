package ru.netcracker.sviridov.utils.observables

import android.support.v7.widget.AppCompatAutoCompleteTextView
import android.text.Editable
import android.text.TextWatcher
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Turkin A. on 14/11/2018.
 */
class RxSearchObservable {

    companion object {

        fun fromView(searchView: AppCompatAutoCompleteTextView): Observable<String> {
            val publishSubject = PublishSubject.create<String>()
            searchView.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s != null && s.isNotEmpty()) {
                        publishSubject.onNext(s.toString())
                    }
                }

            })

            /*searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    publishSubject.onComplete()

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return if (newText != null && newText.isNotEmpty()) {
                        publishSubject.onNext(newText)
                        true
                    } else false
                }

            })*/

            return publishSubject
        }

    }

}