package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.factory.PessoaFactory;
import br.com.leivas.bancoleivas.model.BaseEntity;
import br.com.leivas.bancoleivas.model.auth.Usuario;
import br.com.leivas.bancoleivas.model.fin.LancamentoExtrato;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.leivas.bancoleivas.model.fin.LancamentoExtrato.TipoLancamento.ENTRADA;
import static br.com.leivas.bancoleivas.model.fin.LancamentoExtrato.TipoLancamento.SAIDA;

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
    @Column(unique = true, nullable = false)
    private Long numero;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "PESSOAID", referencedColumnName = "ID")
    private Pessoa pessoa;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USUARIOID", referencedColumnName = "ID")
    private Usuario usuario;
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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean possuiSaldoParaTransacao(Transacao transacao) {
        return this.saldo.compareTo(transacao.getValor()) >= 0;
    }

    public void adicionaFundosTransacao(Transacao transacao) {
        BigDecimal valor = transacao.getValor();
        this.saldo = this.saldo.add(valor);
        this.adicionaLancamentoExtrato(LancamentoExtrato.geraLancamentoExtrato(transacao, ENTRADA));
    }

    public void removeFundosTransacao(Transacao transacao) {
        BigDecimal valor = transacao.getValor();
        this.saldo = this.saldo.subtract(valor);
        this.adicionaLancamentoExtrato(LancamentoExtrato.geraLancamentoExtrato(transacao, SAIDA));
    }

    private void adicionaLancamentoExtrato(LancamentoExtrato lancamentoExtrato) {
        if (this.lancamentosExtrato == null) {
            this.lancamentosExtrato = new ArrayList<>();
        }
        this.lancamentosExtrato.add(lancamentoExtrato);
    }

    @Override
    public Conta fromDTO(ContaDTO dto) {
        return this;
    }

    public void adicionaNumeroConta(NumeroConta numeroConta) {
        this.numero = numeroConta.getNumero();
    }
}
