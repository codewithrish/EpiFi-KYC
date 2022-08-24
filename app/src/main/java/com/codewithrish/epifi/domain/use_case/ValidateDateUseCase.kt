package com.codewithrish.epifi.domain.use_case

import android.util.Log
import com.codewithrish.epifi.common.ValidationResult
import com.codewithrish.epifi.domain.repository.FormValidationRepository
import javax.inject.Inject

class ValidateDateUseCase @Inject constructor(
    private val formValidationRepository: FormValidationRepository
) {
    operator fun invoke(
        date: String
    ): ValidationResult {
        Log.d("TAG", "invoke: date usecase called")
        val isValidDate = formValidationRepository.validateDate(date)
        return if (isValidDate) {
            ValidationResult(successful = true)
        } else {
            ValidationResult(successful = false, errorMessage = "Enter Valid DOB")
        }
    }
}