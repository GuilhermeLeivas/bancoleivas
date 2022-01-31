package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroNacionalDTO extends BaseDTO {

    @NotNull
    @NotEmpty
    private String numero;
    @NotNull
    @NotEmpty
    private String emissor;
    @NotNull
    @NotEmpty
    private CadastroNacional.TipoCadastroNacional tipo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CadastroNacionalDTO that = (CadastroNacionalDTO) o;
        return numero.equals(that.numero) && emissor.equals(that.emissor) && tipo == that.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, emissor, tipo);
    }
}
