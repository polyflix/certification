package fr.polyflix.certification.application.http.port.output

import fr.polyflix.certification.domain.entity.Certificate
import java.util.*

data class CertificateResponse(val certification: CertificationResponse, val firstName: String, val lastName: String, val createdAt: Date) {
    constructor(certificate: Certificate): this(CertificationResponse(certificate.certification),
        certificate.user!!.firstName, certificate.user.lastName, certificate.createdAt)
}
