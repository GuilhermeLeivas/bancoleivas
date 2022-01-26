package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaFisicaDTO;
import br.com.leivas.bancoleivas.factory.CadastroNacionalFactory;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Override
    public Pessoa fromDTO(PessoaDTO dto) {
        PessoaFisicaDTO pessoaFisicaDTO = (PessoaFisicaDTO) dto;
        this.nome = pessoaFisicaDTO.getNome();
        this.dataNascimento = pessoaFisicaDTO.getDataNascimento();
        this.sexo = pessoaFisicaDTO.getSexo();
        this.nomePai = pessoaFisicaDTO.getNomePai();
        this.nomeMae = pessoaFisicaDTO.getNomeMae();
        this.nomeConjugue = pessoaFisicaDTO.getNomeConjugue();
        this.setCadastroNacional(new CadastroNacionalFactory().produce(dto.getCadastroNacional()));
        return this;
    }
}
