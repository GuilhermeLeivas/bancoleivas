package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.factory.PessoaFactory;
import br.com.leivas.bancoleivas.model.BaseEntity;
import br.com.leivas.bancoleivas.model.fin.LancamentoExtrato;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REGCONTA", indexes = {
        @Index(columnList = "numero"),
})
@SequenceGenerator(name = "seqRegConta", sequenceName = "SEQREGCONTA", allocationSize = 1)
public class Conta extends BaseEntity<ContaDTO, Conta> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqRegConta")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "AGENCIAID", referencedColumnName = "ID")
    private Agencia agencia;
    @Column(unique = true, nullable = false)
    private Long numero;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PESSOAID", referencedColumnName = "ID")
    private Pessoa pessoa;
    @Column
    private BigDecimal saldo = BigDecimal.ZERO;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTAID", referencedColumnName = "ID")
    @JsonIgnore
    private List<LancamentoExtrato> lancamentosExtrato;

    public Conta(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean possuiSaldoParaTransacao(Transacao transacao) {
        return this.saldo.compareTo(transacao.getValor()) > 0;
    }

    public void adicionaFundosTransacao(Transacao transacao) {
        BigDecimal valor = transacao.getValor();
        this.saldo = this.saldo.add(valor);
        this.geraLancamentoExtrato(transacao, LancamentoExtrato.TipoLancamento.ENTRADA);
    }

    public void removeFundosTransacao(Transacao transacao) {
        BigDecimal valor = transacao.getValor();
        this.saldo = this.saldo.subtract(valor);
        this.geraLancamentoExtrato(transacao, LancamentoExtrato.TipoLancamento.SAIDA);
    }

    private void geraLancamentoExtrato(Transacao transacao, LancamentoExtrato.TipoLancamento tipo) {
        LancamentoExtrato lancamentoExtrato = new LancamentoExtrato();
        lancamentoExtrato.adicionaConta(this);
        lancamentoExtrato.adicionaTransacaoOrigemPorTipo(transacao, tipo);
    }

    @Override
    public Conta fromDTO(ContaDTO dto) {
        return this;
    }

    public void adicionaNumeroConta(NumeroConta numeroConta) {
        this.numero = numeroConta.getNumero();
    }
}
