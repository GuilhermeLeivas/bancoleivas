package br.com.leivas.bancoleivas.factory;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import br.com.leivas.bancoleivas.model.reg.Pessoa;
import br.com.leivas.bancoleivas.model.reg.PessoaFisica;
import br.com.leivas.bancoleivas.model.reg.PessoaJuridica;

import static br.com.leivas.bancoleivas.model.reg.CadastroNacional.TipoCadastroNacional.CPF;

public class PessoaFactory implements IFactory<PessoaDTO, Pessoa> {
    @Override
    public Pessoa produce(PessoaDTO pessoaDTO) {
        CadastroNacional.TipoCadastroNacional tipo = pessoaDTO.getCadastroNacional().getTipo();
        return tipo == CPF ?
                new PessoaFisica().fromDTO(pessoaDTO) : new PessoaJuridica().fromDTO(pessoaDTO);
    }
}
