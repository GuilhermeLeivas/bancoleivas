package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.dto.reg.CadastroNacionalDTO;
import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGCADASTRONACIONAL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"numero", "emissor", "digito"})})
@SequenceGenerator(name = "seqRegCadastroNacional", sequenceName = "SEQREGCADASTRONACIONAL", allocationSize = 1)
public class CadastroNacional extends BaseEntity<CadastroNacionalDTO, CadastroNacional> implements ICadNacional {

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

    @Override
    public String numero() {
        return this.numero;
    }

    @Override
    public String digito() {
        return this.digito;
    }
}
