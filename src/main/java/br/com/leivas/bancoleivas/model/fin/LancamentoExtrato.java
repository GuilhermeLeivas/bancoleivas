package br.com.leivas.bancoleivas.model.fin;

import br.com.leivas.bancoleivas.dto.fin.LancamentoExtratoDTO;
import br.com.leivas.bancoleivas.model.BaseEntity;
import br.com.leivas.bancoleivas.model.reg.Conta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FINLANCAMENTOEXTRATO")
@SequenceGenerator(name = "seqFinLancamento", sequenceName = "SEQFINLANCAMENTOEXTRATO", allocationSize = 1)
public class LancamentoExtrato extends BaseEntity<LancamentoExtratoDTO, LancamentoExtrato> {

    public enum TipoLancamento {
        ENTRADA, SAIDA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqFinLancamento")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TRANSACAOORIGEMID", referencedColumnName = "ID")
    @JsonIgnore
    private Transacao transacaoOrigem;
    @Column
    private BigDecimal valor;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TipoLancamento tipoLancamento;

    public LancamentoExtrato(Transacao transacao, LancamentoExtrato.TipoLancamento tipo) {
        this.transacaoOrigem = transacao;
        this.tipoLancamento = tipo;
        this.valor = this.defineValorLancamento();
    }

    public static LancamentoExtrato geraLancamentoExtrato(Transacao transacao, LancamentoExtrato.TipoLancamento tipo) {
        return new LancamentoExtrato(transacao, tipo);
    }

    @Override
    public LancamentoExtrato fromDTO(LancamentoExtratoDTO dto) {
        return null;
    }

    private BigDecimal defineValorLancamento() {
        BigDecimal valorTransacao = this.transacaoOrigem.getValor();
        valorTransacao = this.tipoLancamento == TipoLancamento.ENTRADA ? valorTransacao : this.inverteSinalValor(valorTransacao);
        return valorTransacao;
    }

    private BigDecimal inverteSinalValor(BigDecimal valor) {
        return new BigDecimal(-1).multiply(valor);
    }
}
