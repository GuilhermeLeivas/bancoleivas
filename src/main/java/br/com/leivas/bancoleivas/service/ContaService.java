package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.repository.reg.ContaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContaService {
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta novaConta(ContaDTO contaDTO) {
        Conta novaConta = new Conta().fromDTO(contaDTO);
        novaConta = this.contaRepository.save(novaConta);
        return novaConta;
    }
}
