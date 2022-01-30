package br.com.leivas.bancoleivas.model.fin;

import br.com.leivas.bancoleivas.dto.fin.ExtratoDTO;
import br.com.leivas.bancoleivas.model.DTOConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Extrato implements DTOConverter<ExtratoDTO, Extrato> {
    private Long numeroConta;
    private Page<LancamentoExtrato> lancamentos;
    private Date dtInicial;
    private Date dtFinal;

    public void setLancamentos(Page<LancamentoExtrato> lancamentos) {
        this.lancamentos = lancamentos;
    }

    @Override
    public Extrato fromDTO(ExtratoDTO dto) {
        this.numeroConta = dto.getNumeroConta();
        this.dtInicial = dto.getDtInicial();
        this.dtFinal = dto.getDtFinal();
        return this;
    }
}
