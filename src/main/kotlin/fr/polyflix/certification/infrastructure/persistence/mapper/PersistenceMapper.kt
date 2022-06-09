package fr.polyflix.certification.infrastructure.persistence.mapper

interface PersistenceMapper<Domain, Entity> {
    fun toDomain(entity: Entity): Domain

    fun toEntity(domain: Domain): Entity
}
