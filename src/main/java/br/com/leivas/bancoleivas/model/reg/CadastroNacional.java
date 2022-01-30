package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.dto.reg.CadastroNacionalDTO;
import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGCADASTRONACIONAL", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"numero", "emissor", "digito"})})
@SequenceGenerator(name = "seqRegCadastroNacional", sequenceName = "SEQREGCADASTRONACIONAL", allocationSize = 1)
public class CadastroNacional extends BaseEntity<CadastroNacionalDTO, CadastroNacional> {

    public enum TipoCadastroNacional {
        CPF, CNPJ
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqRegCadastroNacional")
    private Long id;
    @Column(nullable = false, length = 9)
    private String numero;
    @Column(nullable = true, length = 4)
    private String nrFilial;
    @Column(nullable = false, length = 2)
    private String digito;
    @Column(nullable = false, length = 4)
    private String emissor;
    @Enumerated(EnumType.ORDINAL)
    private TipoCadastroNacional tipo;

    @Transient
    private String numeroCompleto;

    @Override
    public CadastroNacional fromDTO(CadastroNacionalDTO dto) {
        this.numeroCompleto = dto.getNumero();
        this.tipo = dto.getTipo();
        return this;
    }

}
