CREATE TABLE certifications
(
    id         UUID NOT NULL,
    name       VARCHAR(255),
    approved   BOOLEAN,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_certifications PRIMARY KEY (id)
);