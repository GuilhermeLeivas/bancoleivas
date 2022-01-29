package br.com.leivas.bancoleivas.service;

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

    public Extrato lancamentosPorConta(Extrato extrato, Pageable pageable) {
        final Page<LancamentoExtrato> lancamentos = this.lancamentoExtratoRepository.lancamentosPorConta(extrato, pageable);
        extrato.setLancamentos(lancamentos);
        return extrato;
    }
}
