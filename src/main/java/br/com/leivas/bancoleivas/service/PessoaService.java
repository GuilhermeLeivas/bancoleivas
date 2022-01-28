package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.model.reg.ICadNacional;
import br.com.leivas.bancoleivas.repository.reg.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public boolean pessoaPossuiCadastro(ICadNacional cadNacional) {
        return this.pessoaRepository.findByCadastroNacional(cadNacional.numero()).isPresent();
    }


}
