package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.dto.reg.AgenciaDTO;
import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "REGAGENCIA")
@SequenceGenerator(name = "seqRegAgencia", sequenceName = "SEQREGAGENCIA", allocationSize = 1)
public class Agencia extends BaseEntity<AgenciaDTO, Agencia> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqRegAgencia")
    private Long id;
    @Column(length = 500)
    private String nome;
    @Column(unique = true, nullable = false)
    private Long numero;

    @Override
    public Agencia fromDTO(AgenciaDTO dto) {
        return null;
    }
}
