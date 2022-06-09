package fr.polyflix.certification.application.http.port.output

import fr.polyflix.certification.domain.entity.Certification
import fr.polyflix.certification.domain.entity.CertificationID

data class CertificationResponse(val certificationID: CertificationID, val name: String, val createdAt: String, val updatedAt: String) {
    constructor(certification: Certification): this(certification.id, certification.name, certification.createdAt.toString(), certification.updatedAt.toString())
}
