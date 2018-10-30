package ru.netcracker.sviridov.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import java.io.Serializable

/**
 * Created by Turkin A. on 30/10/2018.
 */
fun Fragment.newInstance() = Fragment()

fun <P : Parcelable> Fragment.newInstanceWithParcelables(map: Map<String, P>): Fragment {
    val fragment = newInstance()
    val args = Bundle()
    for (key in map.keys) args.putParcelable(key, map[key])
    fragment.arguments = args
    return fragment
}

fun <S : Serializable> Fragment.newInstanceWithSerializables(map: Map<String, S>): Fragment {
    val fragment = newInstance()
    val args = Bundle()
    for (key in map.keys) args.putSerializable(key, map[key])
    fragment.arguments = args
    return fragment
}

fun <VM : ViewModel> Fragment.createVM(viewModelTag: String, viewModelClass: Class<VM>) =
    ViewModelProviders.of(this).get(viewModelTag, viewModelClass)