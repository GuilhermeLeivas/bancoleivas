package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class CadastroNacionalDTO extends BaseDTO {

    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "Número do CPF ou do CPJ completo")
    private String numero;
    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "Emissor do documento, como SSP")
    private String emissor;
    @NotNull
    @ApiModelProperty(example = "Tipo do documento 'CPF' ou 'CNPJ'")
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
