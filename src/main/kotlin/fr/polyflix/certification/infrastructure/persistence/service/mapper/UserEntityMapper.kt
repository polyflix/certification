package fr.polyflix.certification.infrastructure.persistence.service.mapper

import fr.polyflix.certification.domain.entity.User
import fr.polyflix.certification.infrastructure.persistence.service.entity.UserEntity

    fun User.Companion.from(entity: UserEntity): User {
        return User(
                userId = entity.id,
                firstName = entity.firstName,
                lastName = entity.lastName
        )
    }

    fun UserEntity.Companion.from(entity: User): UserEntity {
        return UserEntity(
                id = entity.userId,
                firstName = entity.firstName,
                lastName = entity.lastName
        )
    }