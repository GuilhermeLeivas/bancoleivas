package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.dto.fin.ExtratoDTO;
import br.com.leivas.bancoleivas.model.fin.Extrato;
import br.com.leivas.bancoleivas.model.fin.LancamentoExtrato;
import br.com.leivas.bancoleivas.repository.fin.LancamentoExtratoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExtratoService {

    private final LancamentoExtratoRepository lancamentoExtratoRepository;

    public ExtratoService(LancamentoExtratoRepository lancamentoExtratoRepository) {
        this.lancamentoExtratoRepository = lancamentoExtratoRepository;
    }

    public Extrato lancamentosPorConta(ExtratoDTO pedidoExtrato, Pageable pageable) {
        final Page<LancamentoExtrato> lancamentos = this.lancamentoExtratoRepository.lancamentosPorConta(pedidoExtrato, pageable);
        Extrato extrato = new Extrato().fromDTO(pedidoExtrato);
        extrato.setLancamentos(lancamentos);
        return extrato;
    }
}
