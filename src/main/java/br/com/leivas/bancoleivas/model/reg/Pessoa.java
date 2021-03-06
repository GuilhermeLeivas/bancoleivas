package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "REGPESSOA")
@SequenceGenerator(name = "seqRegPessoa", sequenceName = "SEQREGPESSOA", allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa extends BaseEntity<PessoaDTO, Pessoa> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqRegPessoa")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CADASTRONACIONALID", referencedColumnName = "ID", unique = true, nullable = true)
    private CadastroNacional cadastroNacional;

    public void setCadastroNacional(CadastroNacional cadastroNacional) {
        this.cadastroNacional = cadastroNacional;
    }


    public String nomeReferencia() {
        String nomeReferencia = "";
        if (this instanceof PessoaFisica pf) {
            nomeReferencia = pf.getNome();
        } else {
            PessoaJuridica pj = (PessoaJuridica) this;
            nomeReferencia = pj.getNomeFantasia();
        }
        return nomeReferencia;
    }

    @Override
    public Pessoa fromDTO(PessoaDTO dto) {
        if (this instanceof PessoaFisica pf) {
            pf.fromDTO(dto);
        } else {
            PessoaJuridica pj = (PessoaJuridica) this;
            pj.fromDTO(dto);
        }
        return this;
    }
}
