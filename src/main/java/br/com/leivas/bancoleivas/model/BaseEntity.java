package br.com.leivas.bancoleivas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public abstract class BaseEntity<D, E> implements DTOConverter<D, E> {

    @Version
    @JsonIgnore
    private Integer optmisticklock;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date dtCreate;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    private Date dtUpdate;

    @PrePersist
    protected void prePersistBaseEntity() {
        this.dtCreate = new Date();
        this.dtUpdate = new Date();
    }

    @PreUpdate
    protected void preUpdateBaseEntity() {
        this.dtUpdate = new Date();
    }
}