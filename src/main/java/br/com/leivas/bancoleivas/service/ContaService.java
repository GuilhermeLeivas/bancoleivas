package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.model.reg.NumeroConta;
import br.com.leivas.bancoleivas.repository.reg.ContaRepository;
import br.com.leivas.bancoleivas.repository.reg.NumeroContaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContaService {
    private final ContaRepository contaRepository;
    private final NumeroContaRepository numeroContaRepository;

    public ContaService(ContaRepository contaRepository, NumeroContaRepository numeroContaRepository) {
        this.contaRepository = contaRepository;
        this.numeroContaRepository = numeroContaRepository;
    }

    public Conta novaConta(ContaDTO contaDTO) {
        Conta novaConta = new Conta().fromDTO(contaDTO);
        novaConta.adicionaNumeroConta(this.geraNumeroConta());
        novaConta = this.contaRepository.save(novaConta);
        return novaConta;
    }

    private NumeroConta geraNumeroConta() {
        NumeroConta numeroConta = new NumeroConta();
        numeroConta = this.numeroContaRepository.save(numeroConta);
        return numeroConta;
    }
}
