package br.com.leivas.bancoleivas.model.fin;

import br.com.leivas.bancoleivas.dto.fin.TransacaoDTO;
import br.com.leivas.bancoleivas.model.BaseEntity;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.strategy.ITransacaoStrategy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "FINTRANSACAO")
@SequenceGenerator(name = "seqFinTransacao", sequenceName = "SEQFINTRANSACAO", allocationSize = 1)
public class Transacao extends BaseEntity<TransacaoDTO, Transacao> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqFinTransacao")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "CONTAORIGEMID", referencedColumnName = "ID")
    private Conta contaOrigem;
    @ManyToOne
    @JoinColumn(name = "CONTADESTINOID", referencedColumnName = "ID")
    private Conta contaDestino;
    @Column
    private BigDecimal valor;
    @Column
    private Long numeroProtocolo;

    @Transient
    private ITransacaoStrategy executionStrategy;

    @Override
    public Transacao fromDTO(TransacaoDTO dto) {
        this.valor = dto.getValor();
        return this;
    }
}
