import {
  Injectable,
  InternalServerErrorException,
  NotFoundException,
  UnprocessableEntityException
} from "@nestjs/common";
import { Result } from "@swan-io/boxed";
import { CertificationResponse } from "../../application/dto/certification-response.dto";
import { CreateCertificationProps, Certification } from "../../domain/entities/certification.entity";
import { CertificationAlreadyCompletedError } from "../../domain/errors/certification-already-completed.error";
import { CertificationInvalidError } from "../../domain/errors/certification-invalid.error";
import { CertificationRepository } from "../../domain/ports/repositories/certification.repository";

@Injectable()
export class CertificationService {
  constructor(private readonly certificationRepository: CertificationRepository) {}

  private getCertification(id: string): Certification {
    return this.certificationRepository.findOne(id).match({
      Some: (certification) => certification,
      None: () => {
        throw new NotFoundException(`Certification not found with id=${id}`);
      }
    });
  }

  findOne(id: string): CertificationResponse {
    return CertificationResponse.of(this.getCertification(id));
  }

  find(): CertificationResponse[] {
    const certifications = this.certificationRepository.findAll();
    return certifications.map(CertificationResponse.of);
  }

  create(props: CreateCertificationProps): CertificationResponse {
    const certification = Result.fromExecution(() => Certification.create(props)).match({
      Ok: (certification) => certification,
      Error: (error: Error) => {
        switch (error.constructor) {
          case CertificationInvalidError:
            throw new UnprocessableEntityException(error.message);
          default:
            throw new InternalServerErrorException(error.message);
        }
      }
    });

    return this.certificationRepository.save(certification).match({
      Ok: (certification) => CertificationResponse.of(certification),
      Error: (error) => {
        throw error;
      }
    });
  }

  completeCertification(id: string): CertificationResponse {
    const certification = this.getCertification(id);

    const completedCertification = Result.fromExecution(() => certification.complete()).match({
      Ok: () => certification,
      Error: (error: Error) => {
        switch (error.constructor) {
          case CertificationAlreadyCompletedError:
            throw new UnprocessableEntityException(error.message);
          default:
            throw new InternalServerErrorException(error.message);
        }
      }
    });

    return this.certificationRepository.save(completedCertification).match({
      Ok: (certification) => CertificationResponse.of(certification),
      Error: (error) => {
        throw error;
      }
    });
  }

  delete(id: string): CertificationResponse {
    const certification = this.getCertification(id);
    return this.certificationRepository.remove(certification).match({
      Ok: (certification) => CertificationResponse.of(certification),
      Error: (error) => {
        throw error;
      }
    });
  }
}
