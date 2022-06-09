package fr.polyflix.certification.application.http

import fr.polyflix.certification.domain.error.CertificateNotFoundException
import fr.polyflix.certification.domain.error.CertificationNotFoundException
import fr.polyflix.certification.domain.error.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class HttpControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [
        CertificateNotFoundException::class,
        CertificationNotFoundException::class,
        UserNotFoundException::class
    ])
    fun handleNotFound() {}
}
