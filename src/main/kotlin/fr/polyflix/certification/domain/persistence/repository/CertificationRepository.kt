package fr.polyflix.certification.domain.persistence.repository

import fr.polyflix.certification.application.http.port.input.CreateCertificationRequest
import fr.polyflix.certification.domain.entity.*
import org.bouncycastle.cert.ocsp.CertificateID
import java.util.Optional
import java.util.UUID

interface CertificationRepository {
    fun findCertificationById(certificationId: CertificationID): Optional<Certification>
    fun createCertification(certificationDto: CreateCertificationRequest): Optional<Certification>
    fun deleteCertificationById(certificationId: CertificationID)

    fun findCertificateById(certificateId: CertificateID): Optional<Certificate>
    fun findUserCertificates(user: User): List<Certificate>
    fun createCertificateForUser(certification: Certification, user: User): Optional<User>
}
