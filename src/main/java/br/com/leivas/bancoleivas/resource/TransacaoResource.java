package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.fin.TransacaoDTO;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import br.com.leivas.bancoleivas.repository.fin.TransacaoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransacaoResource {

    private final TransacaoRepository transacaoRepository;

    public TransacaoResource(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public Transacao novaTransacao(TransacaoDTO transacaoDTO) {

        return null;
    }
}
