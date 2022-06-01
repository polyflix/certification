package fr.polyflix.certification.infrastructure.persistence.postgres.mapper

import fr.polyflix.certification.domain.entity.Certification
import fr.polyflix.certification.infrastructure.persistence.mapper.PersistenceMapper
import fr.polyflix.certification.infrastructure.persistence.postgres.entity.CertificationEntity
import org.springframework.stereotype.Component

@Component
class CertificationEntityMapper: PersistenceMapper<Certification, CertificationEntity> {
    override fun toDomain(entity: CertificationEntity): Certification {
        return Certification(entity.id, entity.name, entity.approved, entity.createdAt, entity.updatedAt)
    }

    override fun toEntity(domain: Certification): CertificationEntity {
        return CertificationEntity(domain.id, domain.name, domain.approved, domain.createdAt, domain.updatedAt)
    }
}