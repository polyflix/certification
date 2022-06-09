package fr.polyflix.certification.domain.service

import fr.polyflix.certification.domain.entity.*
import fr.polyflix.certification.domain.error.CertificateNotFoundException
import fr.polyflix.certification.domain.error.CertificationNotFoundException
import fr.polyflix.certification.domain.error.UserNotFoundException
import fr.polyflix.certification.domain.persistence.repository.CertificationRepository
import fr.polyflix.certification.domain.persistence.repository.UserRepository
import org.bouncycastle.cert.ocsp.CertificateID
import org.slf4j.LoggerFactory

class CertificationService(private val certificationRepository: CertificationRepository, private val userRepository: UserRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun getCertificationById(certificationID: CertificationID): Certification {
        return getOrFaiLCertificationById(certificationID)
    }

    private fun getOrFaiLCertificationById(certificationID: CertificationID): Certification {
        val certification = certificationRepository.findCertificationById(certificationID)

        return certification.orElseThrow { CertificationNotFoundException(certificationID) }
    }

    fun getUserCertificates(userId: UserID): List<Certificate> {
        val user = getOrFailUserById(userId)

        return certificationRepository.findUserCertificates(user)
    }

    private fun getOrFailUserById(userId: UserID): User {
        val userOption = userRepository.findUserById(userId)
        logger.debug("getOrFailUserByUserId(), userId: $userId, found: ${userOption.isPresent}")

        return userOption.orElseThrow { UserNotFoundException(userId) }
    }

    fun getCertificate(certificateID: CertificateID): Certificate {
        return getOrFailCertificateById(certificateID)
    }

    private fun getOrFailCertificateById(certificateID: CertificateID): Certificate {
        val certificateOption = certificationRepository.findCertificateById(certificateID)
        logger.debug("getORFailCertificateById(), certificateId: $certificateID, found: ${certificateOption.isPresent}")

        return certificateOption.orElseThrow { CertificateNotFoundException(certificateID) }
    }
}
