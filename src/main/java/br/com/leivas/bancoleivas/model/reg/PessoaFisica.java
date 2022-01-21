package br.com.leivas.bancoleivas.model.reg;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGPESSOAFISICA")
public class PessoaFisica extends Pessoa {
    public enum SexoPessoa {
        MASCULINO, FEMININO, NAOINFORMADO
    }

    @Column(nullable = false, length = 500)
    private String nome;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SexoPessoa sexo;
    @Column(nullable = true, length = 500)
    private String nomePai;
    @Column(nullable = false, length = 500)
    private String nomeMae;
    @Column(nullable = true, length = 500)
    private String nomeConjugue;

}
