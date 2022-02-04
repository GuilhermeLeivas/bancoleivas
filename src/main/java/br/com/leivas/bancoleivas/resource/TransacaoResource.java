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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.leivas.bancoleivas.model.fin.ModeloTransacao.ExecutacaoDaTransacao.CLIENTE;
import static br.com.leivas.bancoleivas.model.fin.ModeloTransacao.ExecutacaoDaTransacao.OPERACIONAL;


@RestController
@RequestMapping("/transacao")
public class TransacaoResource {

    private final TransacaoService transacaoService;

    public TransacaoResource(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @ApiOperation(value = "Endpoint utilizado para efetivar uma nova transação feita pelo CLIENTE. Como transferência, saque, etc...")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nova transação retornada", response = Transacao.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 404, message = "Conta de origem ou Conta de destino não encontrada no sistema", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @PostMapping("/cliente/nova")
    @PreAuthorize("hasAuthority('CLIENTE') and hasAuthority('SCOPE_read') and hasAuthority('SCOPE_write')")
    public ResponseEntity<?> novaTransacaoCliente(@RequestBody @Valid TransacaoDTO transacaoDTO) {
        Transacao novaTransacao = this.transacaoService.novaTransacao(transacaoDTO, CLIENTE);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }

    @ApiOperation(value = "Endpoint utilizado para efetivar uma nova transação feita de forma operacional, " +
            "por um caixa eletrônico, um caixa do banco, tais como depósito ou empréstimo.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nova transação retornada", response = Transacao.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 404, message = "Conta de origem ou Conta de destino não encontrada no sistema", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @PostMapping("/operacional/nova")
    @PreAuthorize("(hasAuthority('ADMIN') " +
            "or hasAuthority('CAIXA') " +
            "or hasAuthority('AUTOMATICO') " +
            "or hasAuthority('GERENTE')) and hasAuthority('SCOPE_read') and hasAuthority('SCOPE_write')")
    public ResponseEntity<?> novaTransacaoOperacional(@RequestBody @Valid TransacaoDTO transacaoDTO) {
        Transacao novaTransacao = this.transacaoService.novaTransacao(transacaoDTO, OPERACIONAL);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }
}
