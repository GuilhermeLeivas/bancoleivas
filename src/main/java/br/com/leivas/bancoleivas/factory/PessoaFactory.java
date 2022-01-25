package br.com.leivas.bancoleivas.factory;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaFisicaDTO;
import br.com.leivas.bancoleivas.model.reg.Pessoa;
import br.com.leivas.bancoleivas.model.reg.PessoaFisica;
import br.com.leivas.bancoleivas.model.reg.PessoaJuridica;

public class PessoaFactory implements IFactory<PessoaDTO, Pessoa> {
    @Override
    public Pessoa produce(PessoaDTO pessoaDTO) {
        return pessoaDTO instanceof PessoaFisicaDTO ?
                new PessoaFisica().fromDTO(pessoaDTO) : new PessoaJuridica().fromDTO(pessoaDTO);
    }
}
