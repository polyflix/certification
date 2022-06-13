package fr.polyflix.certification.infrastructure.persistence.postgres.entity

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "certificates")
class CertificateEntity (
    @Id val certificateID: UUID,
    @Column(name = "userid") val userId: UUID,
    @ManyToOne @JoinColumn(name = "certification") val certification: CertificationEntity,
) {
    companion object {}
}
