package br.com.leivas.bancoleivas.model.reg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGNUMEROCONTA")
@SequenceGenerator(name = "seqRegNumeroConta", sequenceName = "SEQREGNUMEROCONTA", allocationSize = 1)
public class NumeroConta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqRegNumeroConta")
    private Long numero;
}
