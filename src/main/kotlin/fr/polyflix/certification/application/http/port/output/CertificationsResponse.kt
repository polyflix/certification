package fr.polyflix.certification.application.http.port.output

import fr.polyflix.certification.domain.entity.Certification

data class CertificationsResponse(val items: List<Certification>, val count: Int) {
    constructor(certifications: List<Certification>): this(certifications, certifications.size)
}
