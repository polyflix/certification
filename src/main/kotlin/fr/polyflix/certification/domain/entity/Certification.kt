package fr.polyflix.certification.domain.entity

import fr.polyflix.certification.application.http.port.input.UpdateCertificationRequest
import java.util.Date
import java.util.UUID

typealias CertificationID = UUID

data class Certification(
    val id: CertificationID,
    var name: String,
    val createdAt: Date,
    val updatedAt: Date
) {
    constructor(certification: Certification, updateCertificationRequest: UpdateCertificationRequest)
        : this(certification.id, updateCertificationRequest.name, certification.createdAt, Date())
    companion object {}
}
