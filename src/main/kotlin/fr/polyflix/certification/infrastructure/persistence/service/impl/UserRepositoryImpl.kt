package fr.polyflix.certification.infrastructure.persistence.service.impl

import fr.polyflix.certification.domain.entity.User
import fr.polyflix.certification.domain.entity.UserID
import fr.polyflix.certification.domain.ports.repository.UserRepository
import fr.polyflix.certification.infrastructure.persistence.service.entity.UserEntity
import fr.polyflix.certification.infrastructure.persistence.service.mapper.from
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import java.util.*


class UserRepositoryImpl(
    private val userUrl: String,
    private val webClient: WebClient = WebClient.builder()
        .baseUrl(userUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build(),
): UserRepository {
    override fun findUserById(userId: UserID): Optional<User> {
        val user = webClient.get()
            .uri("/$userId")
            .retrieve()
            .bodyToMono(UserEntity::class.java)
            .onErrorResume(
                WebClientResponseException::class.java
            ) { _ -> Mono.empty() }
            .blockOptional()
        if (user.isEmpty) {
            return Optional.empty()
        }
        return Optional.of(User.from(user.get()))
    }
}