package br.com.leivas.bancoleivas.dto.reg;

import lombok.Getter;

import java.util.Date;

@Getter
public class PessoaJuridicaDTO extends PessoaDTO {

    private String inscEstadual;
    private String nomeFantasia;
    private String inscMunicipal;
    private Date DataFundacao;
}
