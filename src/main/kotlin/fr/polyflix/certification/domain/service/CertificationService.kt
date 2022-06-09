package fr.polyflix.certification.domain.service

import fr.polyflix.certification.application.http.port.input.CreateCertificationRequest
import fr.polyflix.certification.domain.entity.Certification
import fr.polyflix.certification.domain.entity.CertificationID
import fr.polyflix.certification.domain.error.CertificationCreationFailedException
import fr.polyflix.certification.domain.error.CertificationNotFoundException
import fr.polyflix.certification.domain.persistence.repository.CertificationRepository
import fr.polyflix.certification.domain.persistence.repository.UserRepository
import org.slf4j.LoggerFactory

class CertificationService(
        private val certificationRepository: CertificationRepository,
        private val userRepository: UserRepository
    ) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun getCertificationById(certificationID: CertificationID): Certification {
        return getOrFaiLCertificationById(certificationID)
    }

    private fun getOrFaiLCertificationById(certificationID: CertificationID): Certification {
        val certification = certificationRepository.findCertificationById(certificationID)
        logger.info("getOrFailCertificationById(), certificationID: $certificationID, found: ${certification.isPresent}")

        return certification.orElseThrow { CertificationNotFoundException(certificationID) }
    }

    fun createCertification(certificationRequest: CreateCertificationRequest): Certification {
        return createOrFailCertification(certificationRequest)
    }

    private fun createOrFailCertification(certificationRequest: CreateCertificationRequest): Certification {
        val certification = certificationRepository.createCertification(certificationRequest)

        return certification.orElseThrow { CertificationCreationFailedException() }
    }
}
