package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "REGCADASTRONACIONAL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"numero", "nrFilial", "digito"})})
@SequenceGenerator(name = "seqRegCadastroNacional", sequenceName = "SEQREGCADASTRONACIONAL", allocationSize = 1)
public class CadastroNacional extends BaseEntity {

    public enum TipoCadastroNacional {
        CPF, CNPJ
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGerCadastroNacional")
    private Long id;
    @Column(nullable = false, length = 9)
    private String numero;
    @Column(nullable = true, length = 4)
    private String emissor;
    @Column(nullable = false, length = 2)
    private String digito;
    @Enumerated(EnumType.ORDINAL)
    private TipoCadastroNacional tipo;
}
