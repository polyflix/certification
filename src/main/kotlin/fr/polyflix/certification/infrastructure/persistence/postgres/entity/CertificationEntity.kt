package fr.polyflix.certification.infrastructure.persistence.postgres.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "certifications")
class CertificationEntity(
    @Id val id: UUID,
    @Column val name: String,

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    val createdAt: Date,

    @Column
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    val updatedAt: Date,

    @OneToMany(mappedBy = "certification", cascade = [CascadeType.ALL]) val certificates: List<CertificateEntity>,

    ) {
    companion object {}
}
