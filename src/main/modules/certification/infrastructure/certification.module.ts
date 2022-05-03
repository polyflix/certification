import { Module } from "@nestjs/common";
import { CertificationRepository } from "../domain/ports/repositories/certification.repository";
import { InMemoryCertificationRepository } from "./adapters/repositories/in-mem-certification.repository";
import { CertificationController } from "./controllers/certification.controller";
import { CertificationService } from "./services/certification.service";

@Module({
  controllers: [CertificationController],
  providers: [
    { provide: CertificationRepository, useClass: InMemoryCertificationRepository },
    CertificationService
  ]
})
export class CertificationModule {}
