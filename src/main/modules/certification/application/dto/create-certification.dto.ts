import { IsNotEmpty, IsString } from "class-validator";

export class CreateCertificationDto {
  @IsString()
  @IsNotEmpty()
  title: string;

  @IsString()
  description: string;
}
