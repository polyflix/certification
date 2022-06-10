package fr.polyflix.certification.domain.entity

import java.util.UUID

typealias CertificateID = UUID

data class Certificate(val certificateID: CertificateID, val certification: Certification, val userId: UserID) {
    companion object {}
}
