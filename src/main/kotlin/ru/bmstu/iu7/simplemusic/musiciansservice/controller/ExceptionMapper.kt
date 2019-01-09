package ru.bmstu.iu7.simplemusic.musiciansservice.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.bmstu.iu7.simplemusic.musiciansservice.exception.DuplicateException
import ru.bmstu.iu7.simplemusic.musiciansservice.exception.NotFoundException
import ru.bmstu.iu7.simplemusic.musiciansservice.model.Error

@RestControllerAdvice
class ExceptionMapper {
    @ExceptionHandler(DuplicateException::class)
    fun handleDuplicateException(exception: DuplicateException): ResponseEntity<Error> {
        val error = Error(exception.message!!)
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(error)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ResponseEntity<Error> {
        val error = Error(exception.message!!)
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error)
    }
}
