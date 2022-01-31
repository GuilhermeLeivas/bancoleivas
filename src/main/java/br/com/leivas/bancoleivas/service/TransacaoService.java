package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.dto.fin.TransacaoDTO;
import br.com.leivas.bancoleivas.model.fin.ModeloTransacao;
import br.com.leivas.bancoleivas.model.fin.NumeroProtocolo;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.repository.fin.NumeroProtocoloRepository;
import br.com.leivas.bancoleivas.repository.fin.TransacaoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final NumeroProtocoloRepository numeroProtocoloRepository;
    private final ContaService contaService;
    private final ModeloTransacaoService modeloTransacaoService;

    public TransacaoService(TransacaoRepository transacaoRepository, NumeroProtocoloRepository numeroProtocoloRepository, ContaService contaService, ModeloTransacaoService modeloTransacaoService) {
        this.transacaoRepository = transacaoRepository;
        this.numeroProtocoloRepository = numeroProtocoloRepository;
        this.contaService = contaService;
        this.modeloTransacaoService = modeloTransacaoService;
    }

    public Transacao novaTransacao(TransacaoDTO transacaoDTO, ModeloTransacao.ExecutacaoDaTransacao executacaoDaTransacao) {
        Conta contaOrigem = this.contaService.findContaByNumero(transacaoDTO.getNumeroContaOrigem());
        Conta contaDestino = this.contaService.findContaByNumero(transacaoDTO.getNumeroContaDestino());
        Transacao novaTransacao = new Transacao().fromDTO(transacaoDTO);
        novaTransacao.adicionaContaOrigemContaDestino(contaOrigem, contaDestino);
        novaTransacao.adicionaNumeroProtocolo(this.geraNumeroProtocolo());
        novaTransacao = this.modeloTransacaoService.adicionaTransacaoStrategy(novaTransacao, transacaoDTO.getCodigoTransacao(), executacaoDaTransacao);
        novaTransacao = this.transacaoRepository.save(novaTransacao);
        novaTransacao.executaTransacao();
        this.contaService.atualizaMultiplasContas(contaOrigem, contaDestino);
        return novaTransacao;
    }

    private NumeroProtocolo geraNumeroProtocolo() {
        NumeroProtocolo numeroProtocolo = new NumeroProtocolo();
        numeroProtocolo = this.numeroProtocoloRepository.save(numeroProtocolo);
        return numeroProtocolo;
    }
}
