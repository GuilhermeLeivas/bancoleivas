package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.event.createdResourceDestinationEvent;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.service.ContaService;
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

    @PostMapping("/nova")
    public ResponseEntity<?> novaConta(@RequestBody ContaDTO contaDTO, HttpServletResponse response) {
        Conta novaConta = contaService.novaConta(contaDTO);
        this.publisher.publishEvent(new createdResourceDestinationEvent(this, response, novaConta.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }
}
