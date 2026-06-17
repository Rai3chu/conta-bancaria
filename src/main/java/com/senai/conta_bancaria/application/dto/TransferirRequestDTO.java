package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Conta;
import jakarta.validation.constraints.NotNull;

public record TransferirRequestDTO(
        @NotNull
        Long valorTransferir,
        @NotNull
        Conta contaDestino
) {
}
