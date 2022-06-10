package fr.polyflix.certification.domain.error

import fr.polyflix.certification.domain.entity.CertificateID


class CertificateNotFoundException(certificateId: CertificateID) : DomainException("Certificate $certificateId was not found") {
}
