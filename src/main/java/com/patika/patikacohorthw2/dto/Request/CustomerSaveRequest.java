package com.patika.patikacohorthw2.dto.Request;

import com.patika.patikacohorthw2.model.SectorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest { // DTO da client'den gelen değerleri belirtiyoruz. postmande nasıl atacaksan ona göer uyumlu type seçmelisin.
    private String name;
    private LocalDate registrationDate;
    private SectorEnum sector;
}