package com.codewithrish.epifi.domain.use_case

import com.codewithrish.epifi.domain.repository.FormValidationRepository
import javax.inject.Inject

class ValidateDateUseCase @Inject constructor(
    private val formValidationRepository: FormValidationRepository
) {
    // Overriding invoke function as we need only in functionality from this use case
    operator fun invoke(
        date: String
    ): Boolean {
        return formValidationRepository.validateDate(date)
    }
}