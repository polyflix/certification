package fr.polyflix.certification.infrastructure.persistence.postgres

import fr.polyflix.certification.infrastructure.persistence.postgres.entity.CertificateEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SpringCertificateRepository: JpaRepository<CertificateEntity, UUID> {
    fun findByUserId(userId: UUID): List<CertificateEntity>
    fun findByCertificationId(id: UUID): List<CertificateEntity>
}

