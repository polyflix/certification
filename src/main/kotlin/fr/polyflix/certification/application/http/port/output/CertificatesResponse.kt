package fr.polyflix.certification.application.http.port.output

import fr.polyflix.certification.domain.entity.Certificate

data class CertificatesResponse(val count: Int) {
    constructor(certificates: List<Certificate>): this(certificates.size)
}
