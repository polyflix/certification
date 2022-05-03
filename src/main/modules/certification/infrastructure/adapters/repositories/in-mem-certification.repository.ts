import { Injectable, Logger } from "@nestjs/common";
import { Option, Result } from "@swan-io/boxed";
import { Certification } from "../../../domain/entities/certification.entity";
import { CertificationRepository } from "../../../domain/ports/repositories/certification.repository";

@Injectable()
export class InMemoryCertificationRepository extends CertificationRepository {
  private readonly logger = new Logger(InMemoryCertificationRepository.name);
  private certifications: Certification[] = [];

  findOne(id: string): Option<Certification> {
    this.logger.log(`Retrieving certification with id ${id}`);
    return Option.fromNullable(this.certifications.find((certification) => certification.getId() === id));
  }

  findAll(): Certification[] {
    this.logger.log(`Retrieving certifications`);
    return this.certifications;
  }

  save(certification: Certification): Result<Certification, Error> {
    return Result.fromExecution(() => {
      this.logger.log(
        `Creating new certification with title=${certification.getTitle()}, description=${certification.getDescription()}`
      );

      this.findOne(certification.getId()).match({
        Some: (t) => {
          const index = this.certifications.indexOf(t);
          this.certifications[index] = certification;
        },
        None: () => {
          this.certifications.push(certification);
        }
      });

      return certification;
    });
  }

  remove(certification: Certification): Result<Certification, Error> {
    return Result.fromExecution(() => {
      this.logger.log(`Deleting certification with id=${certification.getTitle()}`);
      this.certifications = this.certifications.filter((t) => t.getId() !== certification.getId());
      return certification;
    });
  }
}
