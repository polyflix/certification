package fr.polyflix.certification.infrastructure.persistence.memory

import fr.polyflix.certification.application.http.port.input.CreateCertificationRequest
import fr.polyflix.certification.domain.entity.*
import fr.polyflix.certification.domain.error.CertificateNotFoundException
import fr.polyflix.certification.domain.error.CertificationNotFoundException
import fr.polyflix.certification.domain.ports.repository.CertificationRepository
import java.util.*

class CertificationRepositoryImpl : CertificationRepository {
    private val certifications = mutableListOf<Certification>()
    private val certificates = mutableListOf<Certificate>()

    override fun findAllCertifications(): List<Certification> {
        return certifications
    }

    override fun findCertificationById(certificationId: CertificationID): Optional<Certification> {
        val certification = certifications.find { it.id == certificationId }
        return Optional.ofNullable(certification)
    }

    override fun createCertification(certificationDto: CreateCertificationRequest): Optional<Certification> {
        val certification = Certification(UUID.randomUUID(), certificationDto.name, Date(), Date())
        val added = certifications.add(certification)

        if (!added) {
            return Optional.empty()
        }

        return Optional.of(certification)
    }

    override fun updateCertification(certification: Certification): Result<Certification> {
        val foundCertificationOpt = findCertificationById(certification.id)

        if (foundCertificationOpt.isEmpty) {
            return Result.failure(CertificationNotFoundException(certification.id))
        }

        val foundCertification = foundCertificationOpt.get()
        foundCertification.name = certification.name

        return Result.success(foundCertification)
    }

    override fun deleteCertification(certification: Certification): Result<Unit> {
        val deletedBool = certifications.removeIf { it.id === certification.id }

        if (!deletedBool) {
            return Result.failure(CertificationNotFoundException(certification.id))
        }

        return Result.success(Unit)
    }

    override fun findCertificateById(certificateId: CertificateID): Optional<Certificate> {
        val certificate = certificates.find { it.certificateID == certificateId }

        return Optional.ofNullable(certificate)
    }

    override fun findUserCertificates(user: User): List<Certificate> {

        return this.certificates.filterIndexed { _, i -> i.userId == user.userId }
    }

    override fun createCertificateForUser(certification: Certification, userId: UserID): Optional<Certificate> {
        val certificate = Certificate(UUID.randomUUID(), userId ,certification)

        certificates.add(certificate)

        return Optional.of(certificate)
    }

    override fun deleteCertificate(certificate: Certificate): Result<Unit> {
        val deletedBool = certificates.removeIf { it.certificateID === certificate.certificateID }

        if (!deletedBool) {
            return Result.failure(CertificateNotFoundException(certificate.certificateID))
        }

        return Result.success(Unit)
    }
}
