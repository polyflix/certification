package fr.polyflix.certification.application.http.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/certifications")
class CertificationController {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun findById(): ResponseEntity<Any> {
        return ResponseEntity.ok("TODO : CRUD")
    }
}