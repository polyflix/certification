import { Certification } from "../entities/certification.entity";

export class CertificationAlreadyCompletedError extends Error {
  constructor(certification: Certification) {
    super(`${certification.getTitle()} already completed.`);
  }
}
