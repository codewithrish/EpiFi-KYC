package com.codewithrish.epifi.domain.use_case

import com.codewithrish.epifi.common.ValidationResult
import com.codewithrish.epifi.domain.repository.FormValidationRepository
import javax.inject.Inject

class ValidatePanNumberUseCase @Inject constructor(
    private val formValidationRepository: FormValidationRepository
) {
    operator fun invoke(
        panNumber: String
    ): ValidationResult {
        val isValidPan = formValidationRepository.validatePanNumber(panNumber)
        return if (isValidPan) {
            ValidationResult(successful = true)
        } else {
            ValidationResult(successful = false, errorMessage = "Enter Valid Pan Number")
        }
    }
}