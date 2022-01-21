package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGPESSOA")
@SequenceGenerator(name = "seqRegPessoa", sequenceName = "SEQREGPESSOA", allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqRegPessoa")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CADASTRONACIONALID", referencedColumnName = "ID", unique = true, nullable = true)
    private CadastroNacional cadastroNacional;
}
