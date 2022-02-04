package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.fin.ExtratoDTO;
import br.com.leivas.bancoleivas.exception.handler.BancoLeivasExceptionHandler;
import br.com.leivas.bancoleivas.model.fin.LancamentoExtrato;
import br.com.leivas.bancoleivas.service.ExtratoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/extrato")
public class ExtratoResource {

    private final ExtratoService extratoService;

    public ExtratoResource(ExtratoService extratoService) {
        this.extratoService = extratoService;
    }

    @ApiOperation(value = "Endpoint utilizado para gerar extrato temporal de uma conta, baseando-se nos dados informados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Extrato retornado com lançamentos de @param dtinicial até @param dtfinal passados no json", response = Page.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 404, message = "Conta não encontrada no sistema", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @GetMapping
    @PreAuthorize("hasAuthority('CLIENTE') and hasAuthority('SCOPE_read')")
    public ResponseEntity<?> extratoPorConta(ExtratoDTO pedidoExtrato, Pageable pageable) {
        Page<LancamentoExtrato> lancamentos = this.extratoService.lancamentosPorConta(pedidoExtrato, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(lancamentos);
    }
}
