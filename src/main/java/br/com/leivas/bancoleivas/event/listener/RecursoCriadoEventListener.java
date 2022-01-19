package br.com.leivas.bancoleivas.event.listener;

import br.com.leivas.bancoleivas.event.RecursoCriadoEvent;
import com.sun.istack.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

/*
Util para adicionar header location após algum recurso ser criado via web service.
 */
@Component
public class RecursoCriadoEventListener implements ApplicationListener<RecursoCriadoEvent> {
    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
        HttpServletResponse response = recursoCriadoEvent.getResponse();
        Long id = recursoCriadoEvent.getId();
        this.adicionarHeaderLocation(response, id);
    }

    private void adicionarHeaderLocation(@NotNull HttpServletResponse response, Long id) { // Lógica para adicionar o header
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri();
        response.setHeader("location", uri.toASCIIString());
    }
}
