package br.com.leivas.bancoleivas.dto.reg;

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
public class PessoaJuridicaDTO extends PessoaDTO {

    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "1234")
    private String inscEstadual;
    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "1234")
    private String inscMunicipal;
    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "Nome de empresa Maneira LTDA")
    private String nomeFantasia;
    @NotNull
    @ApiModelProperty(example = "1992-01-25")
    private Date DataFundacao;
}
