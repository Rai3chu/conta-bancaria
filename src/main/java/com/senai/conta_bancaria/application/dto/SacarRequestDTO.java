package com.senai.conta_bancaria.application.dto;

import jakarta.validation.constraints.NotNull;

public record SacarRequestDTO(
        @NotNull
        long valorSacado
) {
}
