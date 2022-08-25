package com.codewithrish.epifi.domain.repository

interface FormValidationRepository {
    // Validate Pan Number
    fun validatePanNumber(panNumber: String): Boolean
    // Validate Date
    fun validateDate(date: String): Boolean
}