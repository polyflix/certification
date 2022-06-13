package fr.polyflix.certification.infrastructure.persistence.postgres.mapper

import fr.polyflix.certification.domain.entity.Certificate
import fr.polyflix.certification.domain.entity.Certification
import fr.polyflix.certification.infrastructure.persistence.postgres.entity.CertificateEntity
import fr.polyflix.certification.infrastructure.persistence.postgres.entity.CertificationEntity

fun Certificate.Companion.from(entity: CertificateEntity): Certificate {
    val certification = Certification.from(entity.certification)
    return Certificate(certification, entity.userId)
}

fun CertificateEntity.Companion.from(entity: Certificate): CertificateEntity {
    val certification = CertificationEntity.from(entity.certification)
    return CertificateEntity(entity.certificateID, entity.userId, certification)
}

