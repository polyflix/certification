package fr.polyflix.certification.application.http.port.output

import fr.polyflix.certification.domain.entity.Certificate

data class CertificateResponse(val certificate: CertificationResponse, val firstName: String, val lastName: String) {
    constructor(certificate: Certificate): this(CertificationResponse(certificate.certification), "John", "Doe")
}
