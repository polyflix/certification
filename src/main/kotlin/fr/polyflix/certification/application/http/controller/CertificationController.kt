package fr.polyflix.certification.application.http.controller

import fr.polyflix.certification.application.http.port.input.CreateCertificateRequest
import fr.polyflix.certification.application.http.port.input.CreateCertificationRequest
import fr.polyflix.certification.application.http.port.input.UpdateCertificationRequest
import fr.polyflix.certification.application.http.port.output.CertificateResponse
import fr.polyflix.certification.application.http.port.output.CertificatesResponse
import fr.polyflix.certification.application.http.port.output.CertificationResponse
import fr.polyflix.certification.application.http.port.output.CertificationsResponse
import fr.polyflix.certification.domain.entity.CertificateID
import fr.polyflix.certification.domain.entity.CertificationID
import fr.polyflix.certification.domain.entity.UserID
import fr.polyflix.certification.domain.service.CertificateService
import fr.polyflix.certification.domain.service.CertificationService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v2.0.0/certifications")
class CertificationController(
        private val certificationService: CertificationService,
        private val certificateService: CertificateService,
    ) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun findAllCertifications(): ResponseEntity<CertificationsResponse> {
        logger.info("findAllCertifications()")
        val certifications = certificationService.getAllCertifications().map { CertificationResponse(it) }

        return ResponseEntity.ok(CertificationsResponse(certifications))
    }

    @GetMapping("/{id}")
    fun findCertificationById(@PathVariable id: CertificationID): ResponseEntity<CertificationResponse> {
        logger.info("findCertificationById()")
        val certification = certificationService.getCertificationById(id)

        return ResponseEntity.ok(CertificationResponse(certification))
    }

    @PostMapping
    fun createCertification(@RequestBody body: CreateCertificationRequest): ResponseEntity<CertificationResponse> {
        logger.info("createCertification()")
        val certification = certificationService.createCertification(body)

        return ResponseEntity.ok(CertificationResponse(certification))
    }

    @PutMapping("/{id}")
    fun updateCertification(@PathVariable id: CertificationID, @RequestBody body: UpdateCertificationRequest): ResponseEntity<CertificationResponse> {
        logger.info("updateCertification()")
        val certification = certificationService.updateCertification(id, body)

        return ResponseEntity.ok(CertificationResponse(certification))
    }

    @DeleteMapping("/{id}")
    fun deleteCertification(@PathVariable id: CertificationID): ResponseEntity<CertificationResponse> {
        logger.info("deleteCertification()")
        val certification = certificationService.deleteCertification(id)

        return ResponseEntity.ok(CertificationResponse(certification))
    }

    @GetMapping("/certificate/{id}")
    fun findCertificateById(@PathVariable id: CertificateID): ResponseEntity<CertificateResponse> {
        logger.info("findCertificateById()")
        val certificate = certificateService.getCertificate(id)

        return ResponseEntity.ok(CertificateResponse(certificate))
    }

    @GetMapping("/certificate")
    fun findUserCertificates(@RequestHeader("X-User-Id") userId: UserID): ResponseEntity<CertificatesResponse> {
        logger.info("findUserCertificates()")
        val certificates = certificateService.getUserCertificates(userId)

       return ResponseEntity.ok(CertificatesResponse(certificates))
    }

    @PostMapping("/certificate")
    fun createCertificate(@RequestBody body: CreateCertificateRequest): ResponseEntity<CertificateResponse> {
        logger.info("createCertificate()")
        val certificate = certificateService.createCertificateForUser(body)

        return ResponseEntity.ok(CertificateResponse(certificate))
    }

    @DeleteMapping("certificate/{id}")
    fun deleteCertificate(@PathVariable id: CertificateID): ResponseEntity<CertificateResponse> {
        logger.info("deleteCertificate()")
        val certificate = certificateService.deleteCertificate(id)

        return ResponseEntity.ok(CertificateResponse(certificate))
    }
}
