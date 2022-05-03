export class CertificationInvalidError extends Error {
  constructor() {
    super(
      `The certification is invalid. The title must be defined and the certification should not be completed to be valid.`
    );
  }
}
