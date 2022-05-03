import { Test } from "@nestjs/testing";
import { Certification } from "../../../domain/entities/certification.entity";
import { InMemoryCertificationRepository } from "./in-mem-certification.repository";

describe("InMemCertificationRepository", () => {
  let repository: InMemoryCertificationRepository;

  beforeEach(async () => {
    const moduleRef = await Test.createTestingModule({
      providers: [InMemoryCertificationRepository]
    }).compile();

    repository = moduleRef.get<InMemoryCertificationRepository>(InMemoryCertificationRepository);
  });

  describe("findOne", () => {
    it("should find the certification by id", () => {
      const expected = repository
        .save(
          Certification.create({
            title: "My super certification",
            description: "flemme"
          })
        )
        .getWithDefault(undefined);

      const result = repository
        .findOne(expected.getId())
        .getWithDefault(undefined);

      expect(result).toBe(expected);
    });
  });
});
