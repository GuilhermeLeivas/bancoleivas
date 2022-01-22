package br.com.leivas.bancoleivas.model;

import java.io.Serializable;

public interface DTOConverter<D, E> extends Serializable {

    E fromDTO(D dto);
}
