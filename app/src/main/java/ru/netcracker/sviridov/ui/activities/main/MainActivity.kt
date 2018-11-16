package ru.netcracker.sviridov.ui.activities.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.netcracker.sviridov.R
import ru.netcracker.sviridov.databinding.ActivityMainBinding
import ru.netcracker.sviridov.ui.adapters.pager.MainPagerAdapter
import ru.netcracker.sviridov.utils.exts.createVM

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainActivityVM
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = createVM(MainActivityVM.TAG, MainActivityVM::class.java)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.apply {
            vm = mViewModel
            setLifecycleOwner(this@MainActivity)
        }

        mBinding.viewPager.adapter = MainPagerAdapter(supportFragmentManager)
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
