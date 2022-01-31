package br.com.leivas.bancoleivas.dto.reg;

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
public class PessoaJuridicaDTO extends PessoaDTO {

    @NotNull
    @NotEmpty
    private String inscEstadual;
    @NotNull
    @NotEmpty
    private String inscMunicipal;
    @NotNull
    @NotEmpty
    private String nomeFantasia;
    @NotNull
    @NotEmpty
    private Date DataFundacao;
}
