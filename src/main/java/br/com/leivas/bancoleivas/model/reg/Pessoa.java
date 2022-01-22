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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CADASTRONACIONALID", referencedColumnName = "ID", unique = true, nullable = true)
    private CadastroNacional cadastroNacional;

    public void setCadastroNacional(CadastroNacional cadastroNacional) {
        this.cadastroNacional = cadastroNacional;
    }
}
