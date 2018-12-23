package com.example.demo.dto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ErrorResponse(var message: String) {

    companion object {
        fun createNotFoundResponse(e: NotFoundException): ResponseEntity<ErrorResponse> {
            return ResponseEntity<ErrorResponse>(ErrorResponse(e.errorMessage), HttpStatus.NOT_FOUND)
        }
        fun createBadReqestException(e: BadRequestException): ResponseEntity<ErrorResponse> {
            return ResponseEntity<ErrorResponse>(ErrorResponse(e.errorMessage), HttpStatus.BAD_REQUEST)
        }
    }
}