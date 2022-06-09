package fr.polyflix.certification.domain.entity

import java.util.Date
import java.util.UUID

typealias CertificationID = UUID

data class Certification(
    val id: CertificationID,
    val name: String,
    val createdAt: Date,
    val updatedAt: Date
) {
    companion object {}
}
