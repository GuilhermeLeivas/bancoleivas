package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroNacionalDTO extends BaseDTO {

    private String numero;
    private String emissor;
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
