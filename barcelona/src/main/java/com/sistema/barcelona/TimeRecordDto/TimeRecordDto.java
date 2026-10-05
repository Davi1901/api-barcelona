package com.sistema.barcelona.TimeRecordDto;

import jakarta.validation.constraints.NotBlank;

public record TimeRecordDto(@NotBlank String nameTime, String categoria, String nameEstadio, String paisOrigem) {
}
