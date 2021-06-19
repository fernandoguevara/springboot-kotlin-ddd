package com.example.api.infrastructure.filters

import com.example.domain.exceptions.NoteDomainException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest


@RestControllerAdvice
class HttpGlobalExceptionFilter {

    @Value("\${spring.profiles.active}")
    private val activeProfile: String = ""

    private val logger: Logger = LoggerFactory.getLogger(HttpGlobalExceptionFilter::class.java)

    @ExceptionHandler(value = [ Exception::class])
    @ResponseBody
    fun onException(request: HttpServletRequest, exception: Exception): ResponseEntity<Any>{

        logger.error(exception.toString(), exception.message)

        return if (exception is NoteDomainException) {
            val json = ObjectResult(request.servletPath,
            "Please refer to the errors property for additional details.",
            exception.message.toString())
            ResponseEntity(json, HttpStatus.BAD_REQUEST)
        } else {
            val json = JsonErrorResponse(arrayOf("An error occur.Try it again."))

            if(activeProfile == "dev") json.developerMessage = exception

            ResponseEntity(json, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    data class ObjectResult(
        var instance: String,
        var detail: String,
        var errors: String
    )

    data class JsonErrorResponse(
        var messages: Array<String>,
        var developerMessage: Exception? = null
    )

}