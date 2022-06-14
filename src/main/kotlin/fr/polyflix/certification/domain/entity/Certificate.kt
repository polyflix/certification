package fr.polyflix.certification.domain.entity

import java.util.*

typealias CertificateID = UUID

data class Certificate(val certificateID: CertificateID, val userId: UserID, val createdAt: Date, val certification: Certification, val user: User?) {
    constructor(certificateID: CertificateID, userId: UserID, createdAt: Date, certification: Certification) : this(certificateID, userId, createdAt, certification, null)
    companion object {}
}
