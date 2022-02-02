package br.com.leivas.bancoleivas.model.fin;

import br.com.leivas.bancoleivas.dto.fin.ModeloTransacaoDTO;
import br.com.leivas.bancoleivas.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "FINMODELOTRANSACAO",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"codigo", "executacaoDaTransacao", "transacaoStrategy"})})
@SequenceGenerator(name = "seqFinModeloTransacao", sequenceName = "SEQFINMODELOTRANSACAO", allocationSize = 1)
public class ModeloTransacao extends BaseEntity<ModeloTransacaoDTO, ModeloTransacao> {

    public enum ExecutacaoDaTransacao {
        CLIENTE, OPERACIONAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqFinModeloTransacao")
    private Long id;
    @Column
    private Integer codigo;
    @Column
    private String nome;
    @Enumerated(EnumType.ORDINAL)
    @Column
    @JsonIgnore
    private ExecutacaoDaTransacao executacaoDaTransacao;
    @Column(length = 1000)
    @JsonIgnore
    private String transacaoStrategy;

    @Override
    public ModeloTransacao fromDTO(ModeloTransacaoDTO dto) {
        this.codigo = dto.getCodigo();
        this.nome = dto.getNome();
        this.executacaoDaTransacao = dto.getExecutacaoDaTransacao();
        this.transacaoStrategy = dto.getTransacaoStrategy();
        return this;
    }
}
