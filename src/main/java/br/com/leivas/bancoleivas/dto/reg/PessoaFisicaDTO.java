package br.com.leivas.bancoleivas.dto.reg;

import br.com.leivas.bancoleivas.model.reg.PessoaFisica;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class PessoaFisicaDTO extends PessoaDTO {
    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "João da Silva")
    private String nome;
    @NotNull
    @ApiModelProperty(example = "1998-10-02")
    private Date dataNascimento;
    @NotNull
    @ApiModelProperty(example = "MASCULINO")
    private PessoaFisica.SexoPessoa sexo;
    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "Marcos da Silva")
    private String nomePai;
    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "Joana da Silva")
    private String nomeMae;
    @ApiModelProperty(example = "Amanda Fernandes")
    private String nomeConjugue;
}
