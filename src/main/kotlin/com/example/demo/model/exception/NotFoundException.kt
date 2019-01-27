package com.example.demo.dto

import java.lang.Exception

class NotFoundException(var errorMessage: String) : Exception() {

    fun NotFuoundException(errorMessage: String) {
        this.errorMessage = errorMessage
    }
}