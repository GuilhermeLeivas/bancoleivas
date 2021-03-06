package br.com.leivas.bancoleivas.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

@Getter
public class createdResourceDestinationEvent extends ApplicationEvent {

    private final HttpServletResponse response;
    private final Long id;

    public createdResourceDestinationEvent(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }
}
