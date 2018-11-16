package ru.netcracker.sviridov.utils.exts

import android.content.Context

/**
 * Created by Turkin A. on 16/11/2018.
 */
private const val SHARED_PREFERENCES_NAME = "SVIRIDOV_WEATHER"

private const val KEY_WOEID = "woeid"
private const val KEY_DEFAULT_LONDON = 44418L

fun Context.saveCity(woeid: Long?) =
    this
        .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        .edit()
        .putLong(KEY_WOEID, woeid ?: KEY_DEFAULT_LONDON)
        .commit()

fun Context.getCity(): Long =
    this
        .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        .getLong(KEY_WOEID, KEY_DEFAULT_LONDON)

fun Context.clearCity(): Boolean =
    this
        .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        .edit()
        .remove(KEY_WOEID)
        .commit()