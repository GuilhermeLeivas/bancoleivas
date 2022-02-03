package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.exception.custom.ClienteJaCadastradoNoSistema;
import br.com.leivas.bancoleivas.exception.custom.ContaInexistenteException;
import br.com.leivas.bancoleivas.factory.PessoaFactory;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.model.reg.NumeroConta;
import br.com.leivas.bancoleivas.model.reg.Pessoa;
import br.com.leivas.bancoleivas.repository.reg.ContaRepository;
import br.com.leivas.bancoleivas.repository.reg.NumeroContaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class ContaService {
    private final ContaRepository contaRepository;
    private final PessoaService pessoaService;
    private final NumeroContaRepository numeroContaRepository;
    private final UsuarioService usuarioService;

    public ContaService(ContaRepository contaRepository, PessoaService pessoaService, NumeroContaRepository numeroContaRepository, UsuarioService usuarioService) {
        this.contaRepository = contaRepository;
        this.pessoaService = pessoaService;
        this.numeroContaRepository = numeroContaRepository;
        this.usuarioService = usuarioService;
    }

    public Conta novaConta(PessoaDTO pessoaDTO) {
        if (pessoaService.pessoaPossuiCadastro(pessoaDTO.getCadastroNacional().getNumero())) {
            throw new ClienteJaCadastradoNoSistema(String.format("Cliente %s já está cadastrado no sistema!", pessoaDTO.nomeReferencia()));
        }
        Pessoa pessoa = new PessoaFactory().produce(pessoaDTO);
        pessoa = this.pessoaService.salvaPessoa(pessoa);
        Conta novaConta = new Conta(pessoa);
        novaConta.adicionaNumeroConta(this.geraNumeroConta());
        novaConta = this.contaRepository.save(novaConta);
        this.usuarioService.criaUsuarioConta(novaConta, pessoaDTO);
        return novaConta;
    }

    public Conta contaInfo(Long numeroConta) {
        return this.findContaByNumero(numeroConta);
    }

    public void deletaConta(Long numeroConta) {
        Conta conta = this.findContaByNumero(numeroConta);
        this.contaRepository.delete(conta);
    }

    public Conta findContaByNumero(Long numeroConta) {
        Optional<Conta> conta = this.contaRepository.findByNumero(numeroConta);
        if (conta.isEmpty()) {
            throw new ContaInexistenteException(String.format("Conta: %s não se encontra no sistema!", numeroConta));
        }
        return conta.get();
    }

    public void atualizaMultiplasContas(Conta... contas) {
        this.contaRepository.saveAll(Arrays.asList(contas));
    }

    private NumeroConta geraNumeroConta() {
        NumeroConta numeroConta = new NumeroConta();
        numeroConta = this.numeroContaRepository.save(numeroConta);
        return numeroConta;
    }
}
