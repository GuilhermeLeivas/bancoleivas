package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGCADASTRONACIONAL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"numero", "nrFilial", "digito"})})
@SequenceGenerator(name = "seqRegCadastroNacional", sequenceName = "SEQREGCADASTRONACIONAL", allocationSize = 1)
public class CadastroNacional extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGerCadastroNacional")
    private Long id;
    @Column(nullable = false, length = 9)
    private String numero;
    @Column(nullable = true, length = 4)
    private String nrFilial;
    @Column(nullable = false, length = 2)
    private String digito;
    //  @Transient
//    private ICadNacional cadNacional;

}
