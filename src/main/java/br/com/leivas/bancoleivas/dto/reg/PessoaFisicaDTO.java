package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.model.reg.PessoaFisica;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PessoaFisicaDTO extends PessoaDTO {
    private String nome;
    private Date dataNascimento;
    private PessoaFisica.SexoPessoa sexo;
    private String nomePai;
    private String nomeMae;
    private String nomeConjugue;
}
