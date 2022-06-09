package fr.polyflix.certification.application.http.controller

import fr.polyflix.certification.application.http.port.output.CertificateResponse
import fr.polyflix.certification.application.http.port.output.CertificatesResponse
import fr.polyflix.certification.application.http.port.output.CertificationResponse
import fr.polyflix.certification.domain.entity.CertificationID
import fr.polyflix.certification.domain.entity.UserID
import fr.polyflix.certification.domain.service.CertificationService
import org.bouncycastle.cert.ocsp.CertificateID
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/certifications")
class CertificationController(private val certificationService: CertificationService) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/{id}")
    fun findCertificationById(@PathVariable id: CertificationID): ResponseEntity<CertificationResponse> {
        logger.info("findCertificationById()")
        val certification = certificationService.getCertificationById(id)

        return ResponseEntity.ok(CertificationResponse(certification))
    }

    @GetMapping("/certificate/{id}")
    fun findCertificateById(@PathVariable id: CertificateID): ResponseEntity<CertificateResponse> {
        logger.info("findCertificateById()")
        val certificate = certificationService.getCertificate(id)

        return ResponseEntity.ok(CertificateResponse(certificate))
    }

    @GetMapping
    fun findUserCertificates(@RequestHeader("X-User-Id") userId: UserID): ResponseEntity<CertificatesResponse> {
        logger.info("findUserCertificates()")
        val certificates = certificationService.getUserCertificates(userId)

       return ResponseEntity.ok(CertificatesResponse(certificates))
    }
}
