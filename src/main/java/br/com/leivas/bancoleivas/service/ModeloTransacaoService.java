package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.dto.fin.ModeloTransacaoDTO;
import br.com.leivas.bancoleivas.exception.custom.TransacaoNaoEncontradaException;
import br.com.leivas.bancoleivas.model.fin.ModeloTransacao;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import br.com.leivas.bancoleivas.repository.fin.ModeloTransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModeloTransacaoService {

    private final ModeloTransacaoRepository modeloTransacaoRepository;

    public ModeloTransacaoService(ModeloTransacaoRepository modeloTransacaoRepository) {
        this.modeloTransacaoRepository = modeloTransacaoRepository;
    }

    public ModeloTransacao criaNovoModelo(ModeloTransacaoDTO novoModelo) {
        ModeloTransacao modeloTransacao = new ModeloTransacao().fromDTO(novoModelo);
        modeloTransacao = this.modeloTransacaoRepository.save(modeloTransacao);
        return modeloTransacao;
    }

    public Transacao adicionaTransacaoStrategy(Transacao transacao, Integer codigo) {
        Optional<ModeloTransacao> modelo = this.modeloTransacaoRepository.findByCodigo(codigo);
        if (modelo.isEmpty()) {
            throw new TransacaoNaoEncontradaException(String.format("Transação com código %s não encontrada!"));
        }
        final ModeloTransacao modeloTransacao = modelo.get();
        transacao.adicionaModeloTransacao(modeloTransacao);
        return transacao;
    }
}
