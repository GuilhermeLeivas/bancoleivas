package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.repository.reg.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContaService {
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta novaContaFisica(ContaDTO contaDTO) {
        Conta conta = new Conta();
        conta.setNumero(UUID.randomUUID());
        //conta.setPessoa();
        return null;
    }
}
