package com.codewithrish.epifi.data.local.repository

import com.codewithrish.epifi.domain.repository.FormValidationRepository
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FormValidationRepositoryImpl @Inject constructor(

): FormValidationRepository {

    // Validate Pan Number Implementation
    override fun validatePanNumber(panNumber: String): Boolean {
        val pattern = Regex("[A-Z]{5}[0-9]{4}[A-Z]")
        return panNumber.matches(pattern)
    }

    // Validate Date Implementation
    override fun validateDate(date: String): Boolean {
        if (date.length < 8) return false
        return try {
            SimpleDateFormat("ddMMyyyy", Locale.getDefault()).apply {
                isLenient = false
            }.parse(date)
            true
        } catch (e: ParseException) {
            false
        }
    }
}