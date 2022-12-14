package fr.polyflix.certification.domain.ports.repository

import fr.polyflix.certification.domain.entity.User
import fr.polyflix.certification.domain.entity.UserID
import java.util.Optional

interface UserRepository {
    fun findUserById(userId: UserID): Optional<User>
}
