package fr.polyflix.certification.infrastructure.persistence.service.entity

import fr.polyflix.certification.domain.entity.UserID
import java.util.UUID

data class UserEntity(
        val id: UserID,
        val firstName: String,
        val lastName: String,
) {
        constructor() : this(UUID.randomUUID(), "", "",)
        companion object
}
