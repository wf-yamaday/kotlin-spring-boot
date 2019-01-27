package com.example.demo.dto

class BadRequestException(var errorMessage: String) : Exception() {
    fun BadRequestException(errorMessage: String) {
        this.errorMessage = errorMessage
    }
}