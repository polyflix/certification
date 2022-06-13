package fr.polyflix.certification.domain.service

import fr.polyflix.certification.domain.entity.Certificate
import fr.polyflix.certification.domain.entity.CertificateID
import fr.polyflix.certification.domain.entity.User
import fr.polyflix.certification.domain.entity.UserID
import fr.polyflix.certification.domain.error.CertificateNotFoundException
import fr.polyflix.certification.domain.error.UserNotFoundException
import fr.polyflix.certification.domain.ports.repository.CertificationRepository
import fr.polyflix.certification.domain.ports.repository.UserRepository
import org.slf4j.LoggerFactory

class CertificateService(private val certificationRepository: CertificationRepository, private val userRepository: UserRepository) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun getUserCertificates(userId: UserID): List<Certificate> {
        val user = getOrFailUserById(userId)
        return certificationRepository.findUserCertificates(user)
    }

    private fun getOrFailUserById(userId: UserID): User {
        val userOption = userRepository.findUserById(userId)
        logger.debug("getOrFailUserByUserId(), userId: $userId, found: ${userOption.isPresent}")

        return userOption.orElse(User(userId, "", ""))
    }

    fun getCertificate(certificateID: CertificateID): Certificate {
        val certification = getOrFailCertificateById(certificateID)
        val user = getOrCircuitBreakerUserByUserId(certification.userId)

        return certification.copy(user = user)
    }

    private fun getOrFailCertificateById(certificateID: CertificateID): Certificate {
        val certificateOption = certificationRepository.findCertificateById(certificateID)
        logger.debug("getORFailCertificateById(), certificateId: $certificateID, found: ${certificateOption.isPresent}")

        return certificateOption.orElseThrow { CertificateNotFoundException(certificateID) }
    }

    private fun getOrCircuitBreakerUserByUserId(userId: UserID): User {
        val userOption = userRepository.findUserById(userId)
        logger.debug("getOrFailUserByUserId(), userId: $userId, found: ${userOption.isPresent}")

        return userOption.orElse(User(userId, "", ""))
    }
}
