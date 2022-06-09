package fr.polyflix.certification.domain.error

import fr.polyflix.certification.domain.entity.UserID

class UserNotFoundException(userId: UserID) : DomainException("User $userId could not be found") {
}
