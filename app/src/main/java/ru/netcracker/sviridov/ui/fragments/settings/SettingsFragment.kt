package ru.netcracker.sviridov.ui.fragments.settings


import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_settings.*
import ru.netcracker.sviridov.R
import ru.netcracker.sviridov.databinding.FragmentSettingsBinding
import ru.netcracker.sviridov.utils.exts.createVM
import ru.netcracker.sviridov.utils.exts.saveCity


class SettingsFragment : Fragment() {

    private lateinit var mViewModel: SettingsFragmentVM
    private lateinit var mBinding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        mViewModel = createVM(SettingsFragmentVM.TAG, SettingsFragmentVM::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_settings, container, false)
        mBinding.apply {
            vm = mViewModel
            setLifecycleOwner(this@SettingsFragment)
        }

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchCity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.onSearchStateChanged(s.toString())
            }
        })

        searchCity.setOnItemClickListener { parent, view, position, id ->
            val suggestionAdapter = searchCity.adapter
            if (suggestionAdapter != null) {
                context?.saveCity(mViewModel.suggestions.value?.get(position)?.woeid)
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.suggestionTitles.observe(this, Observer {
            val suggestions = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, it)
            searchCity.setAdapter(suggestions)
        })
    }

    companion object {
        const val TAG = "SettingsFragment"

        fun newInstance(): SettingsFragment = SettingsFragment()
    }

}
