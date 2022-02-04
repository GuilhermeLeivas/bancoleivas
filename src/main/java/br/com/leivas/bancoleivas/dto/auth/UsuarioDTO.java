package br.com.leivas.bancoleivas.dto.auth;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class UsuarioDTO extends BaseDTO {
    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "Senha encriptada com Bcrypt para ser salva como senha do usuário")
    private String senhaDesejada;
}
