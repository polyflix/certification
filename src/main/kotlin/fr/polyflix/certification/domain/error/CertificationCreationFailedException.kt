package fr.polyflix.certification.domain.error

// TODO: Gérer l'erreur pour avoir une meilleure aide dans le message
class CertificationCreationFailedException : DomainException("Failed to generate your certification") {
}
