package fr.polyflix.certification.infrastructure.persistence.postgres.mapper

import fr.polyflix.certification.domain.entity.Certificate
import fr.polyflix.certification.domain.entity.Certification
import fr.polyflix.certification.infrastructure.persistence.postgres.entity.CertificateEntity
import fr.polyflix.certification.infrastructure.persistence.postgres.entity.CertificationEntity
import java.util.*

fun Certificate.Companion.from(entity: CertificateEntity): Certificate {
    val certification = Certification.from(entity.certification)
    return Certificate(entity.certificateID, entity.userId, entity.createdAt, certification)
}

fun CertificateEntity.Companion.from(entity: Certificate): CertificateEntity {
    val certification = CertificationEntity.from(entity.certification)
    return CertificateEntity(entity.certificateID, entity.userId, entity.createdAt, certification)
}

