package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.fin.ModeloTransacaoDTO;
import br.com.leivas.bancoleivas.exception.handler.BancoLeivasExceptionHandler;
import br.com.leivas.bancoleivas.model.fin.ModeloTransacao;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.service.ModeloTransacaoService;
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
@RequestMapping("/modelo/transacao")
public class ModeloTransacaoResource {

    private final ModeloTransacaoService modeloTransacaoService;

    public ModeloTransacaoResource(ModeloTransacaoService modeloTransacaoService) {
        this.modeloTransacaoService = modeloTransacaoService;
    }

    @ApiOperation(value = "Endpoint utilizado para criar novos modelos de transação, como Transferência, depósito, etc.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Novo modelo de transação criado", response = ModeloTransacao.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @PostMapping
    public ResponseEntity<?> criaNovoModeloTransacao(@RequestBody ModeloTransacaoDTO novoModelo) {
        ModeloTransacao modeloTransacao = this.modeloTransacaoService.criaNovoModelo(novoModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(modeloTransacao);
    }
}
