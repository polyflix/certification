package fr.polyflix.certification.application.http.port.input

import java.util.*

data class CreateCertificateRequest(
    val userId: UUID,
    val certificationId: UUID,
) {
}
