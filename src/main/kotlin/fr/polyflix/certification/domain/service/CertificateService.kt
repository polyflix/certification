package fr.polyflix.certification.domain.service

import fr.polyflix.certification.application.http.port.input.CreateCertificateRequest
import fr.polyflix.certification.domain.entity.*
import fr.polyflix.certification.domain.error.CertificateCreationFailedException
import fr.polyflix.certification.domain.error.CertificateNotFoundException
import fr.polyflix.certification.domain.error.CertificationNotFoundException
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

    fun createCertificateForUser(createCertificateDto: CreateCertificateRequest): Certificate {
        return createOrFailCertificateForUser(createCertificateDto.certificationId, createCertificateDto.userId)
    }

    private fun createOrFailCertificateForUser(certificationId: CertificationID, userId: UserID): Certificate {
        val certificationOption = certificationRepository.findCertificationById(certificationId)
        val certification = certificationOption.orElseThrow { CertificationNotFoundException(certificationId) }

        val certificateCreatedOption = certificationRepository.createCertificateForUser(certification, userId)
        return certificateCreatedOption.orElseThrow { CertificateCreationFailedException(certificationId, userId) }
    }

    fun deleteCertificate(certificateId: CertificateID): Certificate {
        return deleteOrFailCertificate(certificateId)
    }

    private fun deleteOrFailCertificate(certificateId: CertificateID): Certificate {
        val certificateOption = certificationRepository.findCertificateById(certificateId)
        val certificate = certificateOption.orElseThrow { CertificateNotFoundException(certificateId) }

        certificationRepository.deleteCertificate(certificate)
        return certificate
    }
}
