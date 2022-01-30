package br.com.leivas.bancoleivas.dto.fin;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExtratoDTO extends BaseDTO {

    private Long numeroConta;
    private Date dtInicial;
    private Date dtFinal;
}
