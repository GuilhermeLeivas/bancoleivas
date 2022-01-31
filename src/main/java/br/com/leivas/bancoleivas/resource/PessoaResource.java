package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.exception.handler.BancoLeivasExceptionHandler;
import br.com.leivas.bancoleivas.model.reg.Pessoa;
import br.com.leivas.bancoleivas.service.PessoaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @ApiOperation(value = "Endpoint utilizado para atualizar dados do CLIENTE.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dados do cliente foram atualizados", response = Pessoa.class),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 404, message = "Cliente não possui cadastro no sistema", response = BancoLeivasExceptionHandler.Erro.class),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção", response = BancoLeivasExceptionHandler.Erro.class),
    })
    @PutMapping
    public ResponseEntity<?> atualizaPessoa(@RequestBody @Valid PessoaDTO pessoaDTO) {
        Pessoa pessoaAtualizada = this.pessoaService.atualizaPessoa(pessoaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaAtualizada);
    }
}
