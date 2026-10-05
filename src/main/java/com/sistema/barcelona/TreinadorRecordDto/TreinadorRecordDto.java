package com.sistema.barcelona.TreinadorRecordDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TreinadorRecordDto(@NotBlank String nameTreinador, String nacionalidade, @NotNull int ageTreinador, int experiencia) {
}
