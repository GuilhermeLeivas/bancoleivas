package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.model.reg.PessoaFisica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFisicaDTO extends PessoaDTO {
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @NotEmpty
    private Date dataNascimento;
    @NotNull
    @NotEmpty
    private PessoaFisica.SexoPessoa sexo;
    @NotNull
    @NotEmpty
    private String nomePai;
    @NotNull
    @NotEmpty
    private String nomeMae;
    @NotNull
    @NotEmpty
    private String nomeConjugue;
}
