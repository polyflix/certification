import { Option, Result } from "@swan-io/boxed";
import { Certification } from "../../entities/certification.entity";

export abstract class CertificationRepository {
  abstract findAll(): Certification[];
  abstract findOne(id: string): Option<Certification>;
  abstract save(certification: Certification): Result<Certification, Error>;
  abstract remove(certification: Certification): Result<Certification, Error>;
}
