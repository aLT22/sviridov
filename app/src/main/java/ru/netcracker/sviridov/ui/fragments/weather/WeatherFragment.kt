package ru.netcracker.sviridov.ui.fragments.weather


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_weather.*
import ru.netcracker.sviridov.R
import ru.netcracker.sviridov.api.model.ConsolidatedWeather
import ru.netcracker.sviridov.databinding.FragmentWeatherBinding
import ru.netcracker.sviridov.ui.adapters.recycler.WeatherAdapter
import ru.netcracker.sviridov.utils.exts.createVM
import ru.netcracker.sviridov.utils.exts.getCity

class WeatherFragment : Fragment() {

    private lateinit var mViewModel: WeatherFragmentVM
    private lateinit var mBinding: FragmentWeatherBinding

    private var mAdapter: WeatherAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        mViewModel = createVM(WeatherFragmentVM.TAG, WeatherFragmentVM::class.java)
        mViewModel.getWeather(context?.getCity()!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_weather, container, false)
        mBinding.apply {
            vm = mViewModel
            setLifecycleOwner(this@WeatherFragment)
        }

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        weatherList.apply {
            layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
        }

        mViewModel.weatherResponse.observe(this, Observer {
            if (mAdapter == null) {
                mAdapter = WeatherAdapter(context!!)
                weatherList.adapter = mAdapter
            }
            mAdapter?.weatherResponse = it
            mAdapter?.items?.clear()
            mAdapter?.items?.addAll(it?.weather as ArrayList<ConsolidatedWeather>)
            mAdapter?.notifyDataSetChanged()
        })

        mViewModel.loading.observe(this, Observer {
            swipeToRefresh.isRefreshing = it ?: false
        })

        swipeToRefresh.setOnRefreshListener {
            mViewModel.getWeather(context?.getCity()!!)
        }
    }

    companion object {
        const val TAG = "WeatherFragment"

        fun newInstance(): WeatherFragment = WeatherFragment()
    }

}
