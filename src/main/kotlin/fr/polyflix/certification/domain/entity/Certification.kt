package fr.polyflix.certification.domain.entity

import java.util.Date
import java.util.UUID

class Certification(
    val id: UUID,
    val name: String,
    val approved: Boolean,
    val createdAt: Date,
    val updatedAt: Date
)