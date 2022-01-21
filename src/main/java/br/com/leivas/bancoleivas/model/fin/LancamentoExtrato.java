package br.com.leivas.bancoleivas.model.fin;

import br.com.leivas.bancoleivas.model.BaseEntity;
import br.com.leivas.bancoleivas.model.reg.Conta;
import lombok.*;

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

    public void adicionaConta(Conta conta) {
        this.conta = conta;
    }

    public void adicionaTransacaoOrigemPorTipo(Transacao transacao, TipoLancamento tipoLancamento) {
        this.transacaoOrigem = transacao;
        this.tipoLancamento = tipoLancamento;
    }

    private void defineValorLancamento() {
        BigDecimal valorTransacao = this.transacaoOrigem.getValor();
        this.valor = this.tipoLancamento == TipoLancamento.ENTRADA ? valorTransacao : this.inverteSinalValor(valorTransacao);
    }

    private BigDecimal inverteSinalValor(BigDecimal valor) {
        return new BigDecimal(-1).multiply(valor);
    }
}
