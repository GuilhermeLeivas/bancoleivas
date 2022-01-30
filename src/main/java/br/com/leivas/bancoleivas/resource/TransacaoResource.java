package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.fin.TransacaoDTO;
import br.com.leivas.bancoleivas.exception.handler.BancoLeivasExceptionHandler;
import br.com.leivas.bancoleivas.model.fin.Transacao;
import br.com.leivas.bancoleivas.service.TransacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transacao")
public class TransacaoResource {

    private final TransacaoService transacaoService;

    public TransacaoResource(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @ApiOperation(value = "Endpoint utilizado para efetivar uma nova transação entre contas.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nova transação retornada", response = Transacao.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 404, message = "Conta de origem ou Conta de destino não encontrada no sistema", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @PostMapping
    public ResponseEntity<?> novaTransacao(@RequestBody TransacaoDTO transacaoDTO) {
        Transacao novaTransacao = this.transacaoService.novaTransacao(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }
}
