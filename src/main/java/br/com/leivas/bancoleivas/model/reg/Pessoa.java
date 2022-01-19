package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGPESSOA")
@SequenceGenerator(name = "seqRegPessoa", sequenceName = "SEQREGPESSOA", allocationSize = 1)
public class Pessoa extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqRegPessoa")
    private Long id;
    @Column(length = 500)
    private String nome;
    @Column
    private String documento;
    @Temporal(TemporalType.DATE)
    private Date dtNascimento;
}
