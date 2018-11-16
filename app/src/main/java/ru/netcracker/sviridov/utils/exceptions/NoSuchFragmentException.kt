package ru.netcracker.sviridov.utils.exceptions

/**
 * Created by Turkin A. on 13/11/2018.
 */
class NoSuchFragmentException(
    override val message: String?
) : Exception(message) {

    companion object {
        private const val TAG = "NoSuchFragmentException"

        const val EXCEPTION_MESSAGE = "No such fragment in this ViewPager!"
    }

}