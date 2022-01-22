package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.dto.reg.CadastroNacionalDTO;
import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGCADASTRONACIONAL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"numero", "nrFilial", "digito"})})
@SequenceGenerator(name = "seqRegCadastroNacional", sequenceName = "SEQREGCADASTRONACIONAL", allocationSize = 1)
public class CadastroNacional extends BaseEntity<CadastroNacionalDTO, CadastroNacional> {

    public enum TipoCadastroNacional {
        CPF, CNPJ
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGerCadastroNacional")
    private Long id;
    @Column(nullable = false, length = 9)
    private String numero;
    @Column(nullable = false, length = 2)
    private String digito;
    @Column(nullable = false, length = 4)
    private String emissor;
    @Enumerated(EnumType.ORDINAL)
    private TipoCadastroNacional tipo;

    @Override
    public CadastroNacional fromDTO(CadastroNacionalDTO dto) {
        return null;
    }
}
