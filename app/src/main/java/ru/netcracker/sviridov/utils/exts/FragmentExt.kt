package ru.netcracker.sviridov.utils.exts

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import java.io.Serializable

/**
 * Created by Turkin A. on 30/10/2018.
 */
fun <F : Fragment, P : Parcelable> F.newInstanceWithParcelables(map: Map<String, P>): F {
    val args = Bundle()
    for (key in map.keys) args.putParcelable(key, map[key])
    this.arguments = args
    return this
}

fun <F : Fragment, S : Serializable> F.newInstanceWithSerializables(map: Map<String, S>): F {
    val args = Bundle()
    for (key in map.keys) args.putSerializable(key, map[key])
    this.arguments = args
    return this
}

fun <F : Fragment, VM : ViewModel> F.createVM(viewModelTag: String, viewModelClass: Class<VM>) =
    ViewModelProviders.of(this).get(viewModelTag, viewModelClass)

fun <F : Fragment, VM : AndroidViewModel> F.createVM(viewModelTag: String,
                                                     viewModelClass: Class<VM>) =
    ViewModelProviders.of(this).get(viewModelTag, viewModelClass)