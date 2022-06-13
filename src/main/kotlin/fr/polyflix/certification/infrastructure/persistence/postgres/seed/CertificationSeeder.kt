package fr.polyflix.certification.infrastructure.persistence.postgres.seed

import fr.polyflix.certification.infrastructure.persistence.postgres.SpringCertificationRepository
import fr.polyflix.certification.infrastructure.persistence.postgres.entity.CertificationEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.Date
import java.util.UUID

@Component
class CertificationSeeder(
    @Value("\${seeders.enabled}") private val seedersEnabled: Boolean,
    private val certificationRepository: SpringCertificationRepository
): CommandLineRunner {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun run(vararg args: String?){
        if (!seedersEnabled) return

        val seeds = listOf(
            CertificationEntity(
                UUID.randomUUID(),
                "AWS Associate",
                Date(),
                Date(),
                emptyList()
            ),
            CertificationEntity(
                UUID.randomUUID(),
                "AWS Cloud Expert",
                Date(),
                Date(),
                emptyList()
            ),
            CertificationEntity(
                UUID.randomUUID(),
                "AWS Cloud Trainer",
                Date(),
                Date(),
                emptyList()
            ),
        )

        logger.info("Cleaning seeds entity in table 'certification'")
        certificationRepository.deleteAll(seeds)
        logger.info("Seeding table 'certification' with ${seeds.size} elements.")
        certificationRepository.saveAll(seeds)
    }
}
