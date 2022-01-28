package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.dto.deserialize.PessoaDTODeserializer;
import br.com.leivas.bancoleivas.model.reg.PessoaFisica;
import br.com.leivas.bancoleivas.model.reg.PessoaJuridica;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonDeserialize(using = PessoaDTODeserializer.class)
public abstract class PessoaDTO extends BaseDTO {

    private CadastroNacionalDTO cadastroNacional;


    public String nomeReferencia() {
        String nomeReferencia = "";
        if (this instanceof PessoaFisicaDTO pf) {
            nomeReferencia = pf.getNome();
        } else {
            PessoaJuridicaDTO pj = (PessoaJuridicaDTO) this;
            nomeReferencia = pj.getNomeFantasia();
        }
        return nomeReferencia;
    }

}
