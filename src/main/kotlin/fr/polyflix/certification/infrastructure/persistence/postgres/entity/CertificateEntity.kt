package fr.polyflix.certification.infrastructure.persistence.postgres.entity

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "certificates")
class CertificateEntity(
    @Id
    val certificateID: UUID,

    @Column(name = "userid")
    val userId: UUID,

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    val createdAt: Date,

    @ManyToOne @JoinColumn(name = "certification")
    val certification: CertificationEntity,
) {
    companion object {}
}
