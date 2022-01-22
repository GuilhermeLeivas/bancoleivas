package br.com.leivas.bancoleivas.resource;

import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaResource {

    @PostMapping("fisica/nova")
    public ResponseEntity novaContaFisica(@RequestBody ContaDTO contaDTO) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("juridica/nova")
    public ResponseEntity novaContaJuridica(@RequestBody ContaDTO contaDTO) {

        return ResponseEntity.ok().build();
    }
}
