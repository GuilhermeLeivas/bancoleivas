package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.event.createdResourceDestinationEvent;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.service.ContaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/conta")
public class ContaResource {

    private final ContaService contaService;
    private final ApplicationEventPublisher publisher;

    public ContaResource(ContaService contaService, ApplicationEventPublisher publisher) {
        this.contaService = contaService;
        this.publisher = publisher;
    }

    @ApiOperation(value = "EndPoint utilizado para criar uma nova conta com base nos dados passados de uma pessoa.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nova conta criada"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 409, message = "Cliente já cadastrado no sistema"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping(value = "/nova", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> novaConta(@RequestBody ContaDTO contaDTO, HttpServletResponse response) {
        Conta novaConta = contaService.novaConta(contaDTO);
        this.publisher.publishEvent(new createdResourceDestinationEvent(this, response, novaConta.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @ApiOperation(value = "EndPoint utilizado para retornar informações sobre uma conta, baseando-se no seu número.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Conta e seus dados"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Conta não encontrada no sistema"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/info/{numeroConta}", produces = "application/json")
    public ResponseEntity<?> contaInfo(@PathVariable Long numeroConta) {
        Conta conta = this.contaService.contaInfo(numeroConta);
        return ResponseEntity.status(HttpStatus.OK).body(conta);
    }

    @ApiOperation(value = "EndPoint utilizado remover conta e dados do cliente do sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Conta e dados do cliente foram removidos"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 404, message = "Conta não encontrada no sistema"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/{numeroConta}")
    public ResponseEntity<?> deletaConta(@PathVariable Long numeroConta) {
        this.contaService.deletaConta(numeroConta);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
