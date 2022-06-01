package fr.polyflix.certification.infrastructure.persistence.postgres.impl

import fr.polyflix.certification.domain.entity.Certification
import fr.polyflix.certification.domain.persistence.repository.CertificationRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.SpringCertificationRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.mapper.CertificationEntityMapper
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CertificationRepositoryImpl(
    private val repository: SpringCertificationRepository,
    private val mapper: CertificationEntityMapper
) : CertificationRepository {
    override fun findById(certificationId: UUID): Optional<Certification> {
        return repository
            .findById(certificationId)
            .map { mapper.toDomain(it) }
    }

    override fun deleteById(certificationId: UUID) {
        return repository.deleteById(certificationId)
    }
}