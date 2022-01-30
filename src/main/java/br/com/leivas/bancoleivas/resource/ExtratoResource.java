package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.model.fin.Extrato;
import br.com.leivas.bancoleivas.service.ExtratoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/extrato")
public class ExtratoResource {

    private final ExtratoService extratoService;

    public ExtratoResource(ExtratoService extratoService) {
        this.extratoService = extratoService;
    }

    @ApiOperation(value = "EndPoint utilizado para gerar extrato temporal de uma conta, baseando-se nos dados informados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Extrato retornado com lançamentos de @param dtinicial até @param dtfinal passados no json"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Conta não encontrada no sistema"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping
    public ResponseEntity<?> extratoPorConta(@RequestBody Extrato pedidoExtrato, Pageable pageable) {
        pedidoExtrato = this.extratoService.lancamentosPorConta(pedidoExtrato, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoExtrato);
    }
}
