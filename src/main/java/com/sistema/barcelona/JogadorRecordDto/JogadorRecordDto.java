package com.sistema.barcelona.JogadorRecordDto;

import com.sistema.barcelona.enums.PosicaoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogadorRecordDto(@NotBlank String name, @NotNull int numberShirt, @NotNull PosicaoEnum position, @NotNull Long contratoId, @NotNull Long timeId) {
}
