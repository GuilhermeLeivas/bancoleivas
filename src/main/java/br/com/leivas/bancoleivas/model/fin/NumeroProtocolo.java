package br.com.leivas.bancoleivas.model.fin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FINNUMEROPROTOCOLO")
@SequenceGenerator(name = "seqFinNumeroProtocolo", sequenceName = "SEQFINNUMEROPROTOCOLO", allocationSize = 1)
public class NumeroProtocolo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqFinNumeroProtocolo")
    private Long numero;
}
