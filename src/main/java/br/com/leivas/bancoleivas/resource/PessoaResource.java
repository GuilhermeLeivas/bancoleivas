package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.model.reg.Pessoa;
import br.com.leivas.bancoleivas.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PutMapping
    public ResponseEntity<?> atualizaPessoa(@RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoaAtualizada = this.pessoaService.atualizaPessoa(pessoaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaAtualizada);
    }
}
