package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.fin.TransacaoDTO;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import br.com.leivas.bancoleivas.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transacao")
public class TransacaoResource {

    private final TransacaoService transacaoService;

    public TransacaoResource(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<?> novaTransacao(TransacaoDTO transacaoDTO) {
        Transacao novaTransacao = this.transacaoService.novaTransacao(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }
}
