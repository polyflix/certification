package fr.polyflix.certification.infrastructure.persistence.postgres.impl

import fr.polyflix.certification.application.http.port.input.CreateCertificationRequest
import fr.polyflix.certification.domain.entity.Certificate
import fr.polyflix.certification.domain.entity.Certification
import fr.polyflix.certification.domain.entity.CertificationID
import fr.polyflix.certification.domain.entity.User
import fr.polyflix.certification.domain.persistence.repository.CertificationRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.SpringCertificationRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.mapper.from
import org.bouncycastle.cert.ocsp.CertificateID
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CertificationRepositoryImpl(
    private val repository: SpringCertificationRepository,
) : CertificationRepository {
    override fun findCertificationById(certificationId: CertificationID): Optional<Certification> {
        return repository
            .findById(certificationId)
            .map { Certification.from(it) }
    }

    override fun createCertification(certificationDto: CreateCertificationRequest): Optional<Certification> {
        TODO("Not yet implemented")
    }

    override fun deleteCertificationById(certificationId: CertificationID) {
        return repository.deleteById(certificationId)
    }

    override fun findCertificateById(certificateId: CertificateID): Optional<Certificate> {
        TODO("Not yet implemented")
    }

    override fun findUserCertificates(user: User): List<Certificate> {
        TODO("Not yet implemented")
    }

    override fun createCertificateForUser(certification: Certification, user: User): Optional<User> {
        TODO("Not yet implemented")
    }
}
