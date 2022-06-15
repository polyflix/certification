package fr.polyflix.certification.application.http.port.output

import fr.polyflix.certification.domain.entity.Certificate
import fr.polyflix.certification.domain.entity.CertificateID
import java.util.*

data class CertificateResponse(val id: CertificateID, val certification: CertificationResponse, val firstName: String, val lastName: String, val createdAt: Date) {
    constructor(certificate: Certificate): this(certificate.certificateID ,CertificationResponse(certificate.certification),
        certificate.user!!.firstName, certificate.user.lastName, certificate.createdAt)
}
