package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.*;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.exception.UsuarioNaoEncontradoException;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senai.conta_bancaria.domain.exception.ContaNaoEncontradaException;

import java.util.List;

@Service
public class    ContaService {
    @Autowired
    ContaRepository contaRepository;

    public ContaResponseDTO cadastrarUsuario(ContaRequestDTO contaRequestDTO) {

        return ContaResponseDTO.fromEntity(
                contaRepository.save(
                        contaRequestDTO.toEntity()
                )
        );
    }

    public List<ContaResponseDTO> listarUsuarios() {
        return contaRepository.findAll()
                .stream().map(
                        ContaResponseDTO::fromEntity
                ).toList();
    }

    public ContaResponseDTO buscarUsuarioPorId(Long id) {

        return ContaResponseDTO.fromEntity(contaRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id))
        );
    }

    public ContaResponseDTO atualizarUsuario(Long id, ContaRequestDTO contaRequestDTO) {
        Conta contaAtualizada = contaRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        contaAtualizada.setAgencia(contaRequestDTO.agencia());
        contaAtualizada.setNumero(contaRequestDTO.numero());
        contaAtualizada.setTipo(contaRequestDTO.tipo());
        contaAtualizada.setSaldo(contaRequestDTO.saldo());

        return ContaResponseDTO.fromEntity(contaRepository.save(contaAtualizada));
    }

    public void deletarUsuario(Long id) {

        if(!contaRepository.existsById(id)){
            throw new UsuarioNaoEncontradoException(id);
        }
        contaRepository.deleteById(id);
    }

    public Conta depositarConta(Long id, DepositoRequestDTO depositoRequestDTO){
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException(id));

        conta.depositar(depositoRequestDTO.valorDepositado());
        return contaRepository.save(conta);

    }

    public Conta sacarConta(Long id, SacarRequestDTO sacarRequestDTO) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException(id));

        conta.sacar(sacarRequestDTO.valorSacado());
        return contaRepository.save(conta);
    }




    public Conta transferirConta(Long idContaOrigem, TransferirRequestDTO transferirRequestDTO, Long idContaDestino) {
        Conta contaOrigem = contaRepository.findById(idContaOrigem)
                .orElseThrow(() -> new ContaNaoEncontrada(idContaOrigem));

        Conta contaDestino = contaRepository.findById(idContaDestino)
                .orElseThrow(() -> new ContaNaoEncontrada(idContaDestino));

        contaOrigem.transferir(contaDestino, transferirRequestDTO.valorTransferir());
        contaRepository.save(contaDestino);
        return contaRepository.save(contaOrigem);
    }
}