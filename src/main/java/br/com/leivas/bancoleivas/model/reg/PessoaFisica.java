package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.dto.reg.PessoaFisicaDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "REGPESSOAFISICA")
public class PessoaFisica extends Pessoa<PessoaFisicaDTO, PessoaFisica> {

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

    @Override
    public PessoaFisica fromDTO(PessoaFisicaDTO dto) {
        return null;
    }
}
