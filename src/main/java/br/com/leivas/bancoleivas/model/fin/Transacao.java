package br.com.leivas.bancoleivas.model.fin;

import br.com.leivas.bancoleivas.dto.fin.TransacaoDTO;
import br.com.leivas.bancoleivas.factory.TransacaoStrategyFactory;
import br.com.leivas.bancoleivas.model.BaseEntity;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.strategy.ITransacaoStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FINTRANSACAO")
@SequenceGenerator(name = "seqFinTransacao", sequenceName = "SEQFINTRANSACAO", allocationSize = 1)
public class Transacao extends BaseEntity<TransacaoDTO, Transacao> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqFinTransacao")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "CONTAORIGEMID", referencedColumnName = "ID")
    @JsonIgnore
    private Conta contaOrigem;
    @ManyToOne
    @JoinColumn(name = "CONTADESTINOID", referencedColumnName = "ID")
    @JsonIgnore
    private Conta contaDestino;
    @Column
    private BigDecimal valor;
    @ManyToOne
    @JoinColumn(name = "MODELOTRANSACAOID", referencedColumnName = "ID")
    private ModeloTransacao modeloTransacao;
    @Column
    private Long numeroProtocolo;

    @Transient
    @JsonIgnore
    private ITransacaoStrategy executionStrategy;

    public void executaTransacao() {
        this.executionStrategy.executeStrategy(this);
    }

    public void adicionaContaOrigemContaDestino(Conta contaOrigem, Conta contaDestino) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
    }

    public void adicionaModeloTransacao(ModeloTransacao modeloTransacao) {
        this.modeloTransacao = modeloTransacao;
        this.executionStrategy = new TransacaoStrategyFactory().produce(modeloTransacao.getTransacaoStrategy());
    }

    public void adicionaNumeroProtocolo(NumeroProtocolo numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo.getNumero();
    }

    @Override
    public Transacao fromDTO(TransacaoDTO dto) {
        this.valor = dto.getValor();
        return this;
    }
}
