import { Certification } from "../../domain/entities/certification.entity";

export class CertificationResponse {
  constructor(
    protected id: string,
    protected title: string,
    protected description: string,
    protected status: string
  ) {}

  public static of(certification: Certification): CertificationResponse {
    return new CertificationResponse(
      certification.getId(),
      certification.getTitle(),
      certification.getDescription(),
      certification.isCompleted() ? "DONE" : "TODO"
    );
  }
}
