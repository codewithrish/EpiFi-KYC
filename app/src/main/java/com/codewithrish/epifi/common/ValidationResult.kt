package com.codewithrish.epifi.common

data class ValidationResult (
    val successful: Boolean = false,
    val errorMessage: String? = null
)
