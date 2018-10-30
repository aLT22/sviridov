package ru.netcracker.sviridov.utils

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment

/**
 * Created by Turkin A. on 30/10/2018.
 */
fun Fragment.newInstance() = Fragment()

fun <T : Parcelable> Fragment.newInstance(map: Map<String, T>): Fragment {
    val fragment = newInstance()
    val args = Bundle()
    for (key in map.keys) args.putParcelable(key, map[key])
    fragment.arguments = args
    return fragment
}