package fr.polyflix.certification.domain.error

import org.bouncycastle.cert.ocsp.CertificateID

class CertificateNotFoundException(certificateId: CertificateID) : DomainException("Certificate $certificateId was not found") {
}
