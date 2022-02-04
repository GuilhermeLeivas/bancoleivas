package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.fin.ModeloTransacaoDTO;
import br.com.leivas.bancoleivas.event.createdResourceDestinationEvent;
import br.com.leivas.bancoleivas.exception.handler.BancoLeivasExceptionHandler;
import br.com.leivas.bancoleivas.model.fin.ModeloTransacao;
import br.com.leivas.bancoleivas.service.ModeloTransacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/modelo/transacao")
public class ModeloTransacaoResource {

    private final ModeloTransacaoService modeloTransacaoService;
    private final ApplicationEventPublisher publisher;

    public ModeloTransacaoResource(ModeloTransacaoService modeloTransacaoService, ApplicationEventPublisher publisher) {
        this.modeloTransacaoService = modeloTransacaoService;
        this.publisher = publisher;
    }

    @ApiOperation(value = "Endpoint utilizado para criar novos modelos de transação, como Transferência, depósito, etc.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Novo modelo de transação criado", response = ModeloTransacao.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') and hasAuthority('SCOPE_write')")
    public ResponseEntity<?> criaNovoModeloTransacao(@RequestBody @Valid ModeloTransacaoDTO novoModelo, HttpServletResponse response) {
        ModeloTransacao modeloTransacao = this.modeloTransacaoService.criaNovoModelo(novoModelo);
        this.publisher.publishEvent(new createdResourceDestinationEvent(this, response, modeloTransacao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(modeloTransacao);
    }

    @ApiOperation(value = "Endpoint utilizado para listar todos modelos de transação já criados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista com todos modelos transação", response = List.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @GetMapping
    @PreAuthorize("(hasAuthority('ADMIN') or hasAuthority('GEREMTE') or hasAuthority('CAIXA')) and hasAuthority('SCOPE_read')")
    public ResponseEntity<?> todosModelos() {
        List<ModeloTransacao> todosModelos = this.modeloTransacaoService.listaTodosModelosTransacao();
        return ResponseEntity.status(HttpStatus.OK).body(todosModelos);
    }
}
