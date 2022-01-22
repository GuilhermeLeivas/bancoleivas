package br.com.leivas.bancoleivas.factory;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.model.reg.Pessoa;

public class PessoaFactory implements IFactory<PessoaDTO, Pessoa> {
    @Override
    public Pessoa produce(PessoaDTO pessoaDTO) {

        return null;
    }
}
