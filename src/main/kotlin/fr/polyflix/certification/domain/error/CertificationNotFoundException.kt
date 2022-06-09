package fr.polyflix.certification.domain.error

import fr.polyflix.certification.domain.entity.CertificationID

class CertificationNotFoundException(certificationID: CertificationID): DomainException("Certification $certificationID could not be found") {
}
