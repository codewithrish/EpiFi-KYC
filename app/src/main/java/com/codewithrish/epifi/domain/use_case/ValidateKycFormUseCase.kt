package com.codewithrish.epifi.domain.use_case

import android.util.Log
import com.codewithrish.epifi.common.ValidationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ValidateKycFormUseCase @Inject constructor(
    private val validateDateUseCase: ValidateDateUseCase,
    private val validatePanNumberUseCase: ValidatePanNumberUseCase
) {
    operator fun invoke(
        panNumber: String,
        date: String
    ): Boolean {
        val isValidDate = validateDateUseCase(date)
        val isValidPanNumber = validatePanNumberUseCase(panNumber)
        Log.d("TAG", "validFormObserver: Date ${isValidDate.successful} ${isValidPanNumber.successful}")
        return isValidDate.successful && isValidPanNumber.successful
    }
}

