package br.com.leivas.bancoleivas.dto.reg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaJuridicaDTO extends PessoaDTO {

    private String inscEstadual;
    private String inscMunicipal;
    private String nomeFantasia;
    private Date DataFundacao;
}
