package ru.netcracker.sviridov.utils.exts

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.FragmentActivity
import java.io.Serializable

/**
 * Created by Turkin A. on 30/10/2018.
 */
fun <A : Activity> A.newIntent() = Intent()

fun <A : Activity> A.startActivity(target: Class<A>) {
    val intent = Intent(this, target)
    this.startActivity(intent)
}

fun <A : Activity> A.startActivityForResult(
    target: Class<A>,
    requestCode: Int
) {
    val intent = Intent(this, target)
    this.startActivityForResult(intent, requestCode)
}

fun <A : Activity, P : Parcelable> A.startActivityWithParcelables(
    target: Class<A>,
    parameters: Map<String, P>
) {
    val intent = Intent(this, target)
    val args = Bundle()
    for (key in parameters.keys) args.putParcelable(key, parameters[key])
    intent.putExtras(args)
    this.startActivity(intent)
}

fun <A : Activity, S : Serializable> A.startActivityWithSerializables(
    target: Class<A>,
    parameters: Map<String, S>
) {
    val intent = Intent(this, target)
    val args = Bundle()
    for (key in parameters.keys) args.putSerializable(key, parameters[key])
    intent.putExtras(args)
    this.startActivity(intent)
}

fun <A : Activity, P : Parcelable> A.startActivityForResultWithParcelables(
    target: Class<A>,
    parameters: Map<String, P>,
    requestCode: Int
) {
    val intent = Intent(this, target)
    val args = Bundle()
    for (key in parameters.keys) args.putParcelable(key, parameters[key])
    intent.putExtras(args)
    this.startActivityForResult(intent, requestCode)
}

fun <A : Activity, S : Serializable> A.startActivityForResultWithSerializables(
    target: Class<A>,
    parameters: Map<String, S>,
    requestCode: Int
) {
    val intent = Intent(this, target)
    val args = Bundle()
    for (key in parameters.keys) args.putSerializable(key, parameters[key])
    intent.putExtras(args)
    this.startActivityForResult(intent, requestCode)
}

fun <FA : FragmentActivity, VM : ViewModel> FA.createVM(viewModelTag: String, viewModelClass: Class<VM>) =
    ViewModelProviders.of(this).get(viewModelTag, viewModelClass)