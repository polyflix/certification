CREATE TABLE certificates
(
    certificateid UUID NOT NULL,
    userid        UUID,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    certification UUID,
    CONSTRAINT pk_certificates PRIMARY KEY (certificateid)
);

ALTER TABLE certificates
    ADD CONSTRAINT FK_CERTIFICATES_ON_CERTIFICATION FOREIGN KEY (certification) REFERENCES certifications (id);