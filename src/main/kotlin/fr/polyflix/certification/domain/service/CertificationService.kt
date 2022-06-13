package fr.polyflix.certification.domain.service

import fr.polyflix.certification.application.http.port.input.CreateCertificationRequest
import fr.polyflix.certification.application.http.port.input.UpdateCertificationRequest
import fr.polyflix.certification.domain.entity.Certification
import fr.polyflix.certification.domain.entity.CertificationID
import fr.polyflix.certification.domain.error.CertificationCreationFailedException
import fr.polyflix.certification.domain.error.CertificationNotFoundException
import fr.polyflix.certification.domain.ports.repository.CertificationRepository
import fr.polyflix.certification.domain.ports.repository.UserRepository
import org.slf4j.LoggerFactory

class CertificationService(
    private val certificationRepository: CertificationRepository,
    private val userRepository: UserRepository
    ) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun getAllCertifications(): List<Certification> {
        return certificationRepository.findAllCertifications()
    }

    fun getCertificationById(certificationID: CertificationID): Certification {
        return getOrFailCertificationById(certificationID)
    }

    private fun getOrFailCertificationById(certificationID: CertificationID): Certification {
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

    fun updateCertification(certificationId: CertificationID, certificationRequest: UpdateCertificationRequest): Certification {
        val previousCertification = getOrFailCertificationById(certificationId)
        val newCertification = Certification(previousCertification, certificationRequest)
        return updateOrFailCertification(newCertification)
    }

    private fun updateOrFailCertification(certification: Certification): Certification {
        return certificationRepository.updateCertification(certification).getOrThrow()
    }

    fun deleteCertification(certificationId: CertificationID): Certification {
        val certification = getOrFailCertificationById(certificationId)

        certificationRepository.deleteCertification(certification).getOrThrow()

        return certification
    }
}
