package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaJuridicaDTO;
import br.com.leivas.bancoleivas.factory.CadastroNacionalFactory;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "REGPESSOAJURIDICA")
public class PessoaJuridica extends Pessoa {

    @Column(nullable = false, length = 20)
    private String inscEstadual;
    @Column(nullable = false, length = 20)
    private String inscMunicipal;
    @Column(nullable = false, length = 500)
    private String nomeFantasia;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DataFundacao;

    @Override
    public Pessoa fromDTO(PessoaDTO dto) {
        PessoaJuridicaDTO pessoaJuridicaDTO = (PessoaJuridicaDTO) dto;
        this.inscEstadual = pessoaJuridicaDTO.getInscEstadual();
        this.inscMunicipal = pessoaJuridicaDTO.getInscMunicipal();
        this.nomeFantasia = pessoaJuridicaDTO.getNomeFantasia();
        this.DataFundacao = pessoaJuridicaDTO.getDataFundacao();
        this.setCadastroNacional(new CadastroNacionalFactory().produce(dto.getCadastroNacional()));
        return this;
    }
}
