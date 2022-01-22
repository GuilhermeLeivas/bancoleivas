package br.com.leivas.bancoleivas.model.reg;

import br.com.leivas.bancoleivas.dto.reg.PessoaJuridicaDTO;
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
public class PessoaJuridica extends Pessoa<PessoaJuridicaDTO, PessoaJuridica> {

    @Column(nullable = false, length = 20)
    private String inscEstadual;
    @Column(nullable = false, length = 500)
    private String nomeFantasia;
    @Column(nullable = false, length = 20)
    private String inscMunicipal;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DataFundacao;

    @Override
    public PessoaJuridica fromDTO(PessoaJuridicaDTO dto) {
        return null;
    }
}
