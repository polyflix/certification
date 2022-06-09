package fr.polyflix.certification.domain.entity

import java.util.Date
import java.util.UUID

typealias CertificationID = UUID

class Certification(
    val id: CertificationID,
    val name: String,
    val approved: Boolean,
    val createdAt: Date,
    val updatedAt: Date
)
