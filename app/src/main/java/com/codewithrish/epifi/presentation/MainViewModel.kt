package com.codewithrish.epifi.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithrish.epifi.domain.use_case.ValidateDateUseCase
import com.codewithrish.epifi.domain.use_case.ValidateKycFormUseCase
import com.codewithrish.epifi.domain.use_case.ValidatePanNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val validateKycFormUseCase: ValidateKycFormUseCase,
): ViewModel() {

    private val _isValidForm = Channel<Boolean>()
    val isValidForm = _isValidForm.receiveAsFlow()

    fun validateKycForm(
        panNumber: String,
        date: String
    ) = viewModelScope.launch {
        _isValidForm.send(validateKycFormUseCase(panNumber, date))
    }
}

data class FormData(
    val panNumber: String = "",
    val panError: String? = null,
    val date: String = "",
    val dateError: String? = null
)