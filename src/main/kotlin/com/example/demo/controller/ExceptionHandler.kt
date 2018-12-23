package com.example.demo.controller

import com.example.demo.dto.BadRequestException
import com.example.demo.dto.ErrorResponse
import com.example.demo.dto.NotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

// controllerの例外処理を行うクラス
@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(req: HttpServletRequest, error: NotFoundException): ResponseEntity<ErrorResponse> {
        return ErrorResponse.createNotFoundResponse(error)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(req: HttpServletRequest, error: BadRequestException) : ResponseEntity<ErrorResponse> {
        return ErrorResponse.createBadReqestException(error)
    }


}