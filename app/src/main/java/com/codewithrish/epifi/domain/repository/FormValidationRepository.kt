package com.codewithrish.epifi.domain.repository

interface FormValidationRepository {
    fun validatePanNumber(panNumber: String): Boolean
    fun validateDate(date: String): Boolean
}