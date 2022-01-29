package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.exception.custom.PessoaNaoPossuiCadastroNoSistema;
import br.com.leivas.bancoleivas.model.reg.ICadNacional;
import br.com.leivas.bancoleivas.model.reg.Pessoa;
import br.com.leivas.bancoleivas.repository.reg.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public boolean pessoaPossuiCadastro(ICadNacional cadNacional) {
        return this.pessoaRepository.findByCadastroNacional(cadNacional.numero()).isPresent();
    }

    public Pessoa atualizaPessoa(PessoaDTO pessoaDTO) {
        Optional<Pessoa> pessoaSalva = this.pessoaRepository.findByCadastroNacional(pessoaDTO.getCadastroNacional().getNumero());
        if (pessoaSalva.isEmpty()) {
            throw new PessoaNaoPossuiCadastroNoSistema(String.format("Cliente %s não possui cadastro", pessoaDTO.nomeReferencia()));
        }
        Pessoa pessoa = pessoaSalva.get();
        pessoa.fromDTO(pessoaDTO);
        pessoa = this.pessoaRepository.save(pessoa);
        return pessoa;
    }
}
