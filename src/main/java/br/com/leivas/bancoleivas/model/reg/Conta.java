package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.model.BaseEntity;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGCONTA", indexes = {
        @Index(columnList = "numero"),
})
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
    @Column
    private BigDecimal saldo = BigDecimal.ZERO;

    public boolean possuiSaldoParaTransacao(Transacao transacao) {
        return this.saldo.compareTo(transacao.getValor()) > 0;
    }

    public void adicionaFundos(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public void removeFundos(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }
}
