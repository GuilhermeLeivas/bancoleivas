package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.exception.custom.ClienteJaCadastradoNoSistema;
import br.com.leivas.bancoleivas.exception.custom.ContaInexistenteException;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.model.reg.NumeroConta;
import br.com.leivas.bancoleivas.repository.reg.ContaRepository;
import br.com.leivas.bancoleivas.repository.reg.NumeroContaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ContaService {
    private final ContaRepository contaRepository;
    private final PessoaService pessoaService;
    private final NumeroContaRepository numeroContaRepository;

    public ContaService(ContaRepository contaRepository, PessoaService pessoaService, NumeroContaRepository numeroContaRepository) {
        this.contaRepository = contaRepository;
        this.pessoaService = pessoaService;
        this.numeroContaRepository = numeroContaRepository;
    }

    public Conta novaConta(ContaDTO contaDTO) {
        PessoaDTO pessoaDTO = contaDTO.getPessoa();
        if (pessoaService.pessoaPossuiCadastro(pessoaDTO.getCadastroNacional())) {
            throw new ClienteJaCadastradoNoSistema(String.format("Cliente %s já está cadastrado no sistema!", pessoaDTO.nomeReferencia()));
        }
        Conta novaConta = new Conta().fromDTO(contaDTO);
        novaConta.adicionaNumeroConta(this.geraNumeroConta());
        novaConta = this.contaRepository.save(novaConta);
        return novaConta;
    }

    public Conta contaInfo(Long numeroConta) {
        Optional<Conta> conta = this.contaRepository.findByNumero(numeroConta);
        this.throwsIfContaNotExists(conta, numeroConta);
        return conta.get();
    }

    public void deletaConta(Long numeroConta) {
        Optional<Conta> conta = this.contaRepository.findByNumero(numeroConta);
        this.throwsIfContaNotExists(conta, numeroConta);
        this.contaRepository.delete(conta.get());
    }

    private NumeroConta geraNumeroConta() {
        NumeroConta numeroConta = new NumeroConta();
        numeroConta = this.numeroContaRepository.save(numeroConta);
        return numeroConta;
    }

    private void throwsIfContaNotExists(Optional<Conta> conta, Long numeroContaPassado) {
        if (conta.isEmpty()) {
            throw new ContaInexistenteException(String.format("Conta: %s não se encontra no sistema!", numeroContaPassado));
        }
    }
}
