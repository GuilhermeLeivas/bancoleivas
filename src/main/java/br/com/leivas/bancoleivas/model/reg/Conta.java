package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGCONTA")
@SequenceGenerator(name = "seqRegConta", sequenceName = "SEQREGCONTA", allocationSize = 1)
public class Conta extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqRegConta")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "AGENCIAID", referencedColumnName = "ID")
    private Agencia agencia;
    @Column(unique = true, nullable = false)
    private UUID numero;
    @OneToOne
    @JoinColumn(name = "PESSOAID", referencedColumnName = "ID")
    private Pessoa pessoa;
}
