package fr.polyflix.certification.infrastructure.persistence.memory

import fr.polyflix.certification.application.http.port.input.CreateCertificationRequest
import fr.polyflix.certification.domain.entity.Certificate
import fr.polyflix.certification.domain.entity.CertificateID
import fr.polyflix.certification.domain.entity.Certification
import fr.polyflix.certification.domain.entity.CertificationID
import fr.polyflix.certification.domain.entity.User
import fr.polyflix.certification.domain.persistence.repository.CertificationRepository
import org.slf4j.LoggerFactory
import java.util.*

class CertificationRepositoryImpl : CertificationRepository {
    private val certifications = emptyList<Certification>()
    private val certificates = emptyList<Certificate>()

    override fun findCertificationById(certificationId: CertificationID): Optional<Certification> {
        val certification = certifications.find { it.id == certificationId }
        return Optional.ofNullable(certification)
    }

    override fun createCertification(certificationDto: CreateCertificationRequest): Optional<Certification> {
        val certification = Certification(UUID.randomUUID(), certificationDto.name, Date(), Date())
        val added = certifications.toMutableList().add(certification)

        if (!added) {
            return Optional.empty()
        }

        return Optional.of(certification)
    }

    override fun deleteCertificationById(certificationId: CertificationID) {
        certifications.toMutableList().removeIf { it.id === certificationId }
    }

    override fun findCertificateById(certificateId: CertificateID): Optional<Certificate> {
        val certificate = certificates.find { it.certificateID == certificateId }

        return Optional.ofNullable(certificate)
    }

    override fun findUserCertificates(user: User): List<Certificate> {

        return this.certificates.filterIndexed { _, i -> i.userId == user.userId }
    }

    override fun createCertificateForUser(certification: Certification, user: User): Optional<Certificate> {
        val certificate = Certificate(UUID.randomUUID(), certification, user.userId)

        certificates.toMutableList().add(certificate)

        return Optional.of(certificate)
    }
}
