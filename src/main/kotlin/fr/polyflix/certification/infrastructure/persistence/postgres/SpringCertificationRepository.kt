package fr.polyflix.certification.infrastructure.persistence.postgres

import fr.polyflix.certification.infrastructure.persistence.postgres.entity.CertificationEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SpringCertificationRepository: JpaRepository<CertificationEntity, UUID>