package fr.polyflix.certification.infrastructure.configuration

import fr.polyflix.certification.domain.ports.repository.CertificationRepository
import fr.polyflix.certification.domain.ports.repository.UserRepository
import fr.polyflix.certification.domain.service.CertificateService
import fr.polyflix.certification.domain.service.CertificationService
import fr.polyflix.certification.infrastructure.persistence.postgres.SpringCertificateRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.SpringCertificationRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.impl.CertificationRepositoryImpl
import fr.polyflix.certification.infrastructure.persistence.service.impl.UserRepositoryImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class BeanConfiguration(
    @Autowired
    private val environment: Environment
    ) {

    @Bean
    fun userRepository(
        @Value("\${services.user}") userServiceUrl: String
    ) : UserRepository {
        return UserRepositoryImpl(userServiceUrl)
    }

    @Bean
    fun certificationRepository(certificationRepository: SpringCertificationRepository, certificateRepository: SpringCertificateRepository) : CertificationRepository {
        return CertificationRepositoryImpl(certificationRepository, certificateRepository)
    }

    @Bean
    fun certificationService(certificationRepository: CertificationRepository, userRepository: UserRepository): CertificationService {
        return CertificationService(certificationRepository, userRepository)
    }

    @Bean
    fun certificateService(certificationRepository: CertificationRepository, userRepository: UserRepository): CertificateService {
        return CertificateService(certificationRepository, userRepository)
    }
}
