package fr.polyflix.certification.domain.ports.repository

import fr.polyflix.certification.application.http.port.input.CreateCertificationRequest
import fr.polyflix.certification.domain.entity.*
import java.util.Optional

interface CertificationRepository {
    fun findAllCertifications(): List<Certification>

    fun findCertificationById(certificationId: CertificationID): Optional<Certification>
    fun createCertification(certificationDto: CreateCertificationRequest): Optional<Certification>
    fun updateCertification(certification: Certification): Result<Certification>
    fun deleteCertification(certification: Certification): Result<Unit>

    fun findCertificateById(certificateId: CertificateID): Optional<Certificate>
    fun findUserCertificates(user: User): List<Certificate>
    fun createCertificateForUser(certification: Certification, userId: UserID): Optional<Certificate>
    fun deleteCertificate(certificate: Certificate): Result<Unit>
}
