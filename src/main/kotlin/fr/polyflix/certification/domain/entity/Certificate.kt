package fr.polyflix.certification.domain.entity

import java.util.UUID

typealias CertificateID = UUID

data class Certificate(val certificateID: CertificateID, val userId: UserID, val certification: Certification, val user: User?) {
    constructor(certificateID: CertificateID, userId: UserID, certification: Certification) : this(certificateID, userId, certification, null)
    companion object {}
}
