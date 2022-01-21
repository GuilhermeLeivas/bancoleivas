package br.com.leivas.bancoleivas.model.reg;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import java.util.Date;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "REGPESSOAJURIDICA")
public class PessoaJuridica extends Pessoa {

    @Column(nullable = false, length = 20)
    private String inscEstadual;
    @Column(nullable = false, length = 500)
    private String nomeFantasia;
    @Column(nullable = false, length = 20)
    private String inscMunicipal;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DataFundacao;
}
