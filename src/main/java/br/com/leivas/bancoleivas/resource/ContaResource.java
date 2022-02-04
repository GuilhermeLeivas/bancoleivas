package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.reg.PessoaFisicaDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaJuridicaDTO;
import br.com.leivas.bancoleivas.event.createdResourceDestinationEvent;
import br.com.leivas.bancoleivas.exception.handler.BancoLeivasExceptionHandler;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.service.ContaService;
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

@RestController
@RequestMapping("/conta")
public class ContaResource {

    private final ContaService contaService;
    private final ApplicationEventPublisher publisher;

    public ContaResource(ContaService contaService, ApplicationEventPublisher publisher) {
        this.contaService = contaService;
        this.publisher = publisher;
    }

    @ApiOperation(value = "Endpoint utilizado para criar uma nova conta PESSOA FÍSICA com base nos dados passados de uma pessoa.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nova conta criada", response = Conta.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 409, message = "Cliente já cadastrado no sistema", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @PostMapping(value = "/fisica/nova", consumes = "application/json", produces = "application/json")
    @PreAuthorize("(hasAuthority('ADMIN') or hasAuthority('GERENTE')) and hasAuthority('SCOPE_write')")
    public ResponseEntity<?> novaContaFisica(@RequestBody @Valid PessoaFisicaDTO pessoa, HttpServletResponse response) {
        Conta novaConta = contaService.novaConta(pessoa);
        this.publisher.publishEvent(new createdResourceDestinationEvent(this, response, novaConta.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @ApiOperation(value = "Endpoint utilizado para criar uma nova conta PESSOA JURÍDICA com base nos dados passados de uma pessoa.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Nova conta criada", response = Conta.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 409, message = "Cliente já cadastrado no sistema", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @PostMapping(value = "/juridica/nova", consumes = "application/json", produces = "application/json")
    @PreAuthorize("(hasAuthority('ADMIN') or hasAuthority('GERENTE')) and hasAuthority('SCOPE_write')")
    public ResponseEntity<?> novaContaJuridica(@RequestBody @Valid PessoaJuridicaDTO pessoa, HttpServletResponse response) {
        Conta novaConta = contaService.novaConta(pessoa);
        this.publisher.publishEvent(new createdResourceDestinationEvent(this, response, novaConta.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @ApiOperation(value = "Endpoint utilizado para retornar informações sobre uma conta, baseando-se no seu número.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Conta e seus dados", response = Conta.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 404, message = "Conta não encontrada no sistema", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @GetMapping(value = "/info/{numeroConta}", produces = "application/json")
    @PreAuthorize("(hasAuthority('ADMIN') or hasAuthority('GERENTE') or hasAuthority('GERENTE')) and hasAuthority('SCOPE_read')")
    public ResponseEntity<?> contaInfo(@PathVariable Long numeroConta) {
        Conta conta = this.contaService.contaInfo(numeroConta);
        return ResponseEntity.status(HttpStatus.OK).body(conta);
    }

    @ApiOperation(value = "Endpoint utilizado remover conta e dados do cliente do sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Conta e dados do cliente foram removidos", response = Void.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 404, message = "Conta não encontrada no sistema", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @DeleteMapping("/{numeroConta}")
    @PreAuthorize("(hasAuthority('ADMIN') or hasAuthority('GERENTE') or hasAuthority('GERENTE')) and hasAuthority('SCOPE_write')")
    public ResponseEntity<?> deletaConta(@PathVariable Long numeroConta) {
        this.contaService.deletaConta(numeroConta);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
