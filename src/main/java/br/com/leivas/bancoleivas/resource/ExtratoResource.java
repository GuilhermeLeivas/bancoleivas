package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.model.fin.Extrato;
import br.com.leivas.bancoleivas.service.ExtratoService;
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

    @GetMapping
    public ResponseEntity<?> extratoPorConta(@RequestBody Extrato pedidoExtrato, Pageable pageable) {
        pedidoExtrato = this.extratoService.lancamentosPorConta(pedidoExtrato, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoExtrato);
    }
}
