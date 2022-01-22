package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.model.reg.PessoaFisica;
import lombok.Getter;

import java.util.Date;

@Getter
public class PessoaFisicaDTO extends PessoaDTO {
    private String nome;
    private Date dataNascimento;
    private PessoaFisica.SexoPessoa sexo;
    private String nomePai;
    private String nomeMae;
    private String nomeConjugue;
}
