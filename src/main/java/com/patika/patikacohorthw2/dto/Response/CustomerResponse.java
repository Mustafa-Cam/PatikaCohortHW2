package com.patika.patikacohorthw2.dto.Response;

import com.patika.patikacohorthw2.model.SectorEnum;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private  String id;
    private  String name;
    private  LocalDate registrationDate;
    private  SectorEnum sector;
}
