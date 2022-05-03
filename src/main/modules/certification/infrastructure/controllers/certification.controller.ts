import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Patch,
  Post
} from "@nestjs/common";
import { CreateCertificationDto } from "../../application/dto/create-certification.dto";
import { CertificationService } from "../services/certification.service";

@Controller("certifications")
export class CertificationController {
  constructor(private readonly certificationService: CertificationService) {}

  @Get()
  find() {
    return this.certificationService.find();
  }

  @Get(":id")
  findOne(@Param("id") id: string) {
    return this.certificationService.findOne(id);
  }

  @Post()
  create(@Body() createCertificationDto: CreateCertificationDto) {
    return this.certificationService.create(createCertificationDto);
  }

  @Patch(":id/complete")
  completeOne(@Param("id") id: string) {
    return this.certificationService.completeCertification(id);
  }

  @Delete(":id")
  deleteOne(@Param("id") id: string) {
    return this.certificationService.delete(id);
  }
}
