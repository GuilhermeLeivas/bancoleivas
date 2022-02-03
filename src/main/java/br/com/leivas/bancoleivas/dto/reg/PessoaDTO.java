package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.dto.auth.UsuarioDTO;
import br.com.leivas.bancoleivas.dto.deserialize.PessoaDTODeserializer;
import br.com.leivas.bancoleivas.model.reg.PessoaFisica;
import br.com.leivas.bancoleivas.model.reg.PessoaJuridica;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonDeserialize(using = PessoaDTODeserializer.class)
public abstract class PessoaDTO extends BaseDTO {

    @NotNull
    private CadastroNacionalDTO cadastroNacional;
    @NotNull
    private UsuarioDTO detalhesUsuario;


    public String nomeReferencia() {
        String nomeReferencia;
        if (this instanceof PessoaFisicaDTO pf) {
            nomeReferencia = pf.getNome();
        } else {
            PessoaJuridicaDTO pj = (PessoaJuridicaDTO) this;
            nomeReferencia = pj.getNomeFantasia();
        }
        return nomeReferencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaDTO pessoaDTO = (PessoaDTO) o;
        return cadastroNacional.equals(pessoaDTO.cadastroNacional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cadastroNacional);
    }
}
