package com.sistema.barcelona.PatrocinioRecordDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PatrocinioRecordDto(@NotBlank String nameEmpresa, String tipoPatrocinio, String dataVigencia, @NotNull
                                  BigDecimal valorContrato) {
}
