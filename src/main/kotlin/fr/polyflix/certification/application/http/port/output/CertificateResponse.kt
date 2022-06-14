package fr.polyflix.certification.application.http.port.output

import fr.polyflix.certification.domain.entity.Certificate

data class CertificateResponse(val certification: CertificationResponse, val firstName: String, val lastName: String) {
    constructor(certificate: Certificate): this(CertificationResponse(certificate.certification),
        certificate.user!!.firstName, certificate.user.lastName)
}
