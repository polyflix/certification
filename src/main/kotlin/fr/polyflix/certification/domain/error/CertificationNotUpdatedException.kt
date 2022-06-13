package fr.polyflix.certification.domain.error

class CertificationNotUpdatedException(val id: String, private val reason: String)
    : DomainException("Could not update certification ($id), reason: $reason") {
}
