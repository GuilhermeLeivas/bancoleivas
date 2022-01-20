package br.com.leivas.bancoleivas.model.fin;

import br.com.leivas.bancoleivas.model.BaseEntity;
import br.com.leivas.bancoleivas.model.reg.Conta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FINLANCAMENTOEXTRATO")
@SequenceGenerator(name = "seqFinLancamento", sequenceName = "SEQFINLANCAMENTOEXTRATO", allocationSize = 1)
public class LancamentoExtrato extends BaseEntity {

    public enum TipoLancamento {
        ENTRADA, SAIDA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqRegConta")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CONTAORIGEM", referencedColumnName = "ID")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "TRANSACAOORIGEMID", referencedColumnName = "ID")
    private Transacao transacaoOrigem;

    @Column
    private BigDecimal valor;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TipoLancamento tipoLancamento;
}
