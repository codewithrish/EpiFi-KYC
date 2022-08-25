package com.codewithrish.epifi.domain.use_case

import javax.inject.Inject

class ValidateKycFormUseCase @Inject constructor(
    private val validateDateUseCase: ValidateDateUseCase,
    private val validatePanNumberUseCase: ValidatePanNumberUseCase
) {
    // Overriding invoke function as we need only in functionality from this use case
    operator fun invoke(
        panNumber: String,
        date: String
    ): Boolean {
        return validateDateUseCase(date) && validatePanNumberUseCase(panNumber)
    }
}

