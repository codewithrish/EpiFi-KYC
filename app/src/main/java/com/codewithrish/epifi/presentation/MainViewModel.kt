package com.codewithrish.epifi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithrish.epifi.domain.use_case.ValidateKycFormUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val validateKycFormUseCase: ValidateKycFormUseCase,
): ViewModel() {

    // Private channel to emit values
    private val _isValidForm = Channel<Boolean>()
    // Receiving channel as flow to expose to UI
    val isValidForm = _isValidForm.receiveAsFlow()

    // Invoke for validation UseCase
    fun validateKycForm(
        panNumber: String,
        date: String
    ) = viewModelScope.launch {
        _isValidForm.send(validateKycFormUseCase(panNumber, date))
    }
}