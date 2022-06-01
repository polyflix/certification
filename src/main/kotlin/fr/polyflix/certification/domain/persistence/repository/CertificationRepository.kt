package fr.polyflix.certification.domain.persistence.repository

import fr.polyflix.certification.domain.entity.Certification
import java.util.Optional
import java.util.UUID

interface CertificationRepository {
    fun findById(certificationId: UUID): Optional<Certification>
    fun deleteById(certificationId: UUID)
}