package br.com.leivas.bancoleivas.dto.fin;

import br.com.leivas.bancoleivas.dto.BaseDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoDTO extends BaseDTO {

    private Long numeroConta;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date dtInicial;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date dtFinal;
}
