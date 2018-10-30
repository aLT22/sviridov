package ru.netcracker.sviridov.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

/**
 * Created by Turkin A. on 30/10/2018.
 */
fun Activity.newIntent() = Intent()

fun <A : Activity> Activity.startActivity(target: Class<A>) {
    val intent = Intent(this, target)
    this.startActivity(intent)
}

fun <A : Activity> Activity.startActivityForResult(
    target: Class<A>,
    requestCode: Int
) {
    val intent = Intent(this, target)
    this.startActivityForResult(intent, requestCode)
}

fun <A : Activity, P : Parcelable> Activity.startActivity(
    target: Class<A>,
    parameters: Map<String, P>
) {
    val intent = Intent(this, target)
    val args = Bundle()
    for (key in parameters.keys) args.putParcelable(key, parameters[key])
    intent.putExtras(args)
    this.startActivity(intent)
}

fun <A : Activity, P : Parcelable> Activity.startActivityForResult(
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