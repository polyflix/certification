package fr.polyflix.certification.infrastructure.persistence.postgres.mapper

import fr.polyflix.certification.domain.entity.Certification
import fr.polyflix.certification.infrastructure.persistence.postgres.entity.CertificationEntity

fun Certification.Companion.from(entity: CertificationEntity): Certification {
    return Certification(entity.id, entity.name, entity.createdAt, entity.updatedAt)
}

fun CertificationEntity.Companion.from(entity: Certification): CertificationEntity {
    return CertificationEntity(entity.id, entity.name, entity.createdAt, entity.updatedAt)
}

