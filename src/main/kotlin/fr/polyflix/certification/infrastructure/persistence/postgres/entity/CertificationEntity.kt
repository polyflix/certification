package fr.polyflix.certification.infrastructure.persistence.postgres.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.Date
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

@Entity
@Table(name= "certifications")
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
) {
    companion object {}
}
