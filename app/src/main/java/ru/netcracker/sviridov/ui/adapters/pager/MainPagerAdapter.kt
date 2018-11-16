package ru.netcracker.sviridov.ui.adapters.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.netcracker.sviridov.ui.fragments.settings.SettingsFragment
import ru.netcracker.sviridov.ui.fragments.weather.WeatherFragment
import ru.netcracker.sviridov.utils.exceptions.NoSuchFragmentException

/**
 * Created by Turkin A. on 13/11/2018.
 */
class MainPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(p0: Int): Fragment =
        when (p0) {
            PAGE_WEATHER -> WeatherFragment.newInstance()
            PAGE_SETTINGS -> SettingsFragment.newInstance()
            else -> throw NoSuchFragmentException(NoSuchFragmentException.EXCEPTION_MESSAGE)
        }

    override fun getCount(): Int = PAGES_COUNT

    override fun getPageTitle(position: Int): CharSequence? =
        when (position) {
            PAGE_WEATHER -> PAGE_WEATHER_TITLE
            PAGE_SETTINGS -> PAGE_SETTINGS_TITLE
            else -> STUB
        }

    companion object {
        const val TAG = "MainPagerAdapter"

        const val PAGES_COUNT = 2

        const val PAGE_WEATHER = 0
        const val PAGE_SETTINGS = 1

        const val PAGE_WEATHER_TITLE = "Weather Details"
        const val PAGE_SETTINGS_TITLE = "Weather Settings"

        const val STUB = ""
    }

}