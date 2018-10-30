package ru.netcracker.sviridov.ui.activities.main

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import ru.netcracker.sviridov.R
import ru.netcracker.sviridov.utils.createVM

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = createVM(MainActivityVM.TAG, MainActivityVM::class.java)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
