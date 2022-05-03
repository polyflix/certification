import { v4 as uuid } from "uuid";
import { CertificationAlreadyCompletedError } from "../errors/certification-already-completed.error";
import { CertificationInvalidError } from "../errors/certification-invalid.error";

export interface CreateCertificationProps {
  title: string;
  description: string;
}

export class Certification {
  private constructor(
    private readonly id: string,
    private title: string,
    private description: string,
    private completed: boolean
  ) {}

  static create(props: CreateCertificationProps): Certification {
    const certification = new Certification(uuid(), props.title, props.description, false);
    if (!certification.validate()) {
      throw new CertificationInvalidError();
    }
    return certification;
  }

  validate(): boolean {
    // Here add some logic to validate the domain entity is valid
    // A certification is valid only if the title is not empty and the certification is not completed at the creation
    return this.getTitle() !== "" && !!this.getTitle() && !this.isCompleted();
  }

  /**
   * Complete the certification.
   */
  complete() {
    if (this.completed) {
      throw new CertificationAlreadyCompletedError(this);
    }
    this.completed = true;
  }

  getId() {
    return this.id;
  }

  getTitle() {
    return this.title;
  }

  getDescription() {
    return this.description;
  }

  isCompleted(): boolean {
    return this.completed;
  }
}
