package fr.polyflix.certification.domain.entity

import java.util.UUID

typealias CertificateID = UUID

data class Certificate(val certificateID: CertificateID, val userId: UserID, val certification: Certification, val user: User?) {
    constructor(certification: Certification, user: User) : this(certification.id, user.userId, certification, user)
    constructor(certification: Certification, userId: UserID) : this(certification.id, userId, certification, null)
    companion object {}
}
