package fr.polyflix.certification.infrastructure.persistence.memory

import fr.polyflix.certification.domain.entity.User
import fr.polyflix.certification.domain.entity.UserID
import fr.polyflix.certification.domain.persistence.repository.UserRepository
import java.util.*

class UserRepositoryImpl : UserRepository {
    override fun findUserById(userId: UserID): Optional<User> {
        val user = User(UUID.randomUUID(), "John", "Doe")
        return Optional.of(user)
    }
}
