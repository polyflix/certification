package fr.polyflix.certification.infrastructure.configuration

import fr.polyflix.certification.domain.persistence.repository.CertificationRepository
import fr.polyflix.certification.domain.persistence.repository.UserRepository
import fr.polyflix.certification.domain.service.CertificateService
import fr.polyflix.certification.domain.service.CertificationService
import fr.polyflix.certification.infrastructure.persistence.memory.UserRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {
    @Bean
    fun userRepository() : UserRepository {
        return UserRepositoryImpl()
    }

    @Bean
    fun certificationService( certificationRepository: CertificationRepository, userRepository: UserRepository): CertificationService {
        return CertificationService(certificationRepository, userRepository)
    }

    @Bean
    fun certificateService(certificationRepository: CertificationRepository, userRepository: UserRepository): CertificateService {
        return CertificateService(certificationRepository, userRepository)
    }
}
