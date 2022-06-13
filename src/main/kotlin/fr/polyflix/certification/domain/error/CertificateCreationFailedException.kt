package fr.polyflix.certification.domain.error

import fr.polyflix.certification.domain.entity.CertificationID
import fr.polyflix.certification.domain.entity.UserID

class CertificateCreationFailedException(certificationID: CertificationID, userID: UserID) : DomainException("Failed to create certificate for user $userID and certification $certificationID") {
}
