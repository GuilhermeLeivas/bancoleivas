package br.com.leivas.bancoleivas.model.fin;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Date;

@Getter
@Builder
public class Extrato implements Serializable {
    private Long numeroConta;
    private Page<LancamentoExtrato> lancamentos;
    private Date dtInicial;
    private Date dtFinal;

    public void setLancamentos(Page<LancamentoExtrato> lancamentos) {
        this.lancamentos = lancamentos;
    }
}
