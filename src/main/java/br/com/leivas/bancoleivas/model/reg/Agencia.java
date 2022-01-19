package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGAGENCIA")
@SequenceGenerator(name = "seqRegAgencia", sequenceName = "REGAGENCIA", allocationSize = 1)
public class Agencia extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqRegAgencia")
    private Long id;
    @Column(length = 500)
    private String nome;
    @Column(unique = true, nullable = false)
    private Long numero;
}