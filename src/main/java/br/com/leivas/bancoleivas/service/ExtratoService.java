package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.dto.fin.ExtratoDTO;
import br.com.leivas.bancoleivas.model.fin.LancamentoExtrato;
import br.com.leivas.bancoleivas.repository.fin.LancamentoExtratoRepository;
import br.com.leivas.bancoleivas.util.date.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ExtratoService {

    private final LancamentoExtratoRepository lancamentoExtratoRepository;

    public ExtratoService(LancamentoExtratoRepository lancamentoExtratoRepository) {
        this.lancamentoExtratoRepository = lancamentoExtratoRepository;
    }

    public Page<LancamentoExtrato> lancamentosPorConta(ExtratoDTO pedidoExtrato, Pageable pageable) {
        Date dtInicial = pedidoExtrato.getDtInicial();
        Date dtFinal = pedidoExtrato.getDtFinal();
        dtFinal = DataUtils.ehOMesmoDia(dtInicial, dtFinal) ? DataUtils.dataProximoDia(dtFinal) : dtFinal;
        pedidoExtrato.setDtFinal(dtFinal);
        return this.lancamentoExtratoRepository.lancamentosPorConta(pedidoExtrato, pageable);
    }
}
