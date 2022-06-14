package fr.polyflix.certification.infrastructure.persistence.postgres.impl

import fr.polyflix.certification.application.http.port.input.CreateCertificationRequest
import fr.polyflix.certification.domain.entity.*
import fr.polyflix.certification.domain.ports.repository.CertificationRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.SpringCertificateRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.SpringCertificationRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.entity.CertificateEntity
import fr.polyflix.certification.infrastructure.persistence.postgres.entity.CertificationEntity
import fr.polyflix.certification.infrastructure.persistence.postgres.mapper.from
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CertificationRepositoryImpl(
    private val certificationRepository: SpringCertificationRepository,
    private val certificateRepository: SpringCertificateRepository,
) : CertificationRepository {

    override fun findAllCertifications(): List<Certification> {
        return certificationRepository.findAll().map { Certification.from(it) }
    }

    override fun findCertificationById(certificationId: CertificationID): Optional<Certification> {
        return certificationRepository
            .findById(certificationId)
            .map { Certification.from(it) }
    }

    override fun createCertification(certificationDto: CreateCertificationRequest): Optional<Certification> {
        val certification = Certification(UUID.randomUUID(), certificationDto.name, Date(), Date())
        val entity = CertificationEntity.from(certification)
        val added = certificationRepository
            .save(entity)
        return Optional.of(Certification.from(added))
    }

    override fun updateCertification(certification: Certification): Result<Certification> {
        val entity = CertificationEntity.from(certification)
        val updatedCertification = certificationRepository.save(entity)
        return Result.success(Certification.from(updatedCertification))
    }

    override fun deleteCertification(certification: Certification): Result<Unit> {
        val deletion = certificationRepository.deleteById(certification.id)
        return Result.success(Unit)
    }

    override fun findCertificateById(certificateId: CertificateID): Optional<Certificate> {
        return certificateRepository
            .findById(certificateId)
            .map { Certificate.from(it) }
    }

    override fun findUserCertificates(user: User): List<Certificate> {
        return certificateRepository
            .findByUserId(user.userId)
            .map { Certificate.from(it) }
    }

    override fun createCertificateForUser(certification: Certification, userId: UserID): Optional<Certificate> {
        val model = Certificate(UUID.randomUUID(), userId, Date(), certification)
        val entity = CertificateEntity.from(model)
        val added = certificateRepository
            .save(entity)
        return Optional.of(Certificate.from(added))
    }

    override fun deleteCertificate(certificate: Certificate): Result<Unit> {
        certificateRepository.deleteById(certificate.certificateID)
        return Result.success(Unit)
    }
}
