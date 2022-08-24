package com.codewithrish.epifi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.codewithrish.epifi.R
import com.codewithrish.epifi.common.hideKeyboard
import com.codewithrish.epifi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TextWatcher, View.OnFocusChangeListener {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var panNumber: String
    private lateinit var day: String
    private lateinit var month: String
    private lateinit var year: String

    private var panFocus: Boolean = false
    private var dayFocus: Boolean = false
    private var monthFocus: Boolean = false
    private var yearFocus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        attachTextWatchers()
        attachFocusListeners()

        validFormObserver()
        capitalizePanInput()
    }

    private fun attachTextWatchers() {
        binding.etPanNumber.addTextChangedListener(this)
        binding.etDay.addTextChangedListener(this)
        binding.etMonth.addTextChangedListener(this)
        binding.etYear.addTextChangedListener(this)
    }

    private fun attachFocusListeners() {
        binding.etPanNumber.onFocusChangeListener = this
        binding.etDay.onFocusChangeListener = this
        binding.etMonth.onFocusChangeListener = this
        binding.etYear.onFocusChangeListener = this
    }

    private fun validFormObserver() {
        lifecycleScope.launchWhenCreated {
            mainViewModel.isValidForm.collectLatest {
                if (it) {
                    applicationContext.hideKeyboard(binding.root)
                }
            }
        }
    }

    private fun capitalizePanInput() {
        binding.etPanNumber.filters = arrayOf(
            InputFilter.AllCaps(),
            InputFilter.LengthFilter(10)
        )
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        when (v?.id) {
            binding.etPanNumber.id -> {
                resetFocus(
                    panFocus = true,
                    dayFocus = false,
                    monthFocus = false,
                    yearFocus = false
                )
            }
            binding.etDay.id -> {
                resetFocus(
                    panFocus = false,
                    dayFocus = true,
                    monthFocus = false,
                    yearFocus = false
                )
                if (!hasFocus && binding.etDay.text.length == 1)
                    binding.etDay.text.insert(0,getString(R.string.zero))
            }
            binding.etMonth.id -> {
                resetFocus(
                    panFocus = false,
                    dayFocus = false,
                    monthFocus = true,
                    yearFocus = false
                )
                if (!hasFocus && binding.etMonth.text.length == 1)
                    binding.etMonth.text.insert(0,getString(R.string.zero))
            }
            binding.etYear.id -> {
                resetFocus(
                    panFocus = false,
                    dayFocus = false,
                    monthFocus = false,
                    yearFocus = true
                )
            }
        }
    }

    private fun resetFocus(panFocus: Boolean, dayFocus: Boolean, monthFocus: Boolean, yearFocus: Boolean) {
        this.panFocus = panFocus
        this.dayFocus = dayFocus
        this.monthFocus = monthFocus
        this.yearFocus = yearFocus
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        // Get Text Values
        panNumber = binding.etPanNumber.text.toString()
        day = binding.etDay.text.toString()
        month = binding.etMonth.text.toString()
        year = binding.etYear.text.toString()

        // Calculate Date
        val date = day.plus(month).plus(year)

        // Change focus entering value4
        if (panFocus && panNumber.length == 10) binding.etDay.requestFocus()
        if (dayFocus && day.length == 2) binding.etMonth.requestFocus()
        if (monthFocus && month.length == 2) binding.etYear.requestFocus()

        // Change focus on deleting value
//        if (yearFocus && year.isEmpty()) binding.etMonth.requestFocus()
//        if (monthFocus && month.isEmpty()) binding.etDay.requestFocus()
//        if (dayFocus && day.isEmpty()) binding.etPanNumber.requestFocus()

        // Call Validation Method
        mainViewModel.validateKycForm(
            panNumber = panNumber,
            date = date
        )
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // TODO("Not yet implemented")
    }

    override fun afterTextChanged(s: Editable?) {
        // TODO("Not yet implemented")
    }

}