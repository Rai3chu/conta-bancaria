package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Conta;

public record TransferirResponseDTO(
        Long valorTranferir,
        Conta contaDestino
) {
}
