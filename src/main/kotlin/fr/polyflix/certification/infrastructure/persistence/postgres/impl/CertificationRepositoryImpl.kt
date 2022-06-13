package fr.polyflix.certification.infrastructure.persistence.postgres.impl

import fr.polyflix.certification.application.http.port.input.CreateCertificationRequest
import fr.polyflix.certification.domain.entity.*
import fr.polyflix.certification.domain.ports.repository.CertificationRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.SpringCertificationRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.mapper.from
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CertificationRepositoryImpl(
    private val repository: SpringCertificationRepository,
) : CertificationRepository {

    override fun findAllCertifications(): List<Certification> {
        TODO("Not yet implemented")
    }

    override fun findCertificationById(certificationId: CertificationID): Optional<Certification> {
        return repository
            .findById(certificationId)
            .map { Certification.from(it) }
    }

    override fun createCertification(certificationDto: CreateCertificationRequest): Optional<Certification> {
        TODO("Not yet implemented")
    }

    override fun updateCertification(certification: Certification): Result<Certification> {
        TODO("Not yet implemented")
    }

    override fun deleteCertification(certification: Certification): Result<Unit> {
        val deletion = repository.deleteById(certification.id)
        return Result.success(Unit)
    }

    override fun findCertificateById(certificateId: CertificateID): Optional<Certificate> {
        TODO("Not yet implemented")
    }

    override fun findUserCertificates(user: User): List<Certificate> {
        TODO("Not yet implemented")
    }

    override fun createCertificateForUser(certification: Certification, user: User): Optional<Certificate> {
        TODO("Not yet implemented")
    }
}
