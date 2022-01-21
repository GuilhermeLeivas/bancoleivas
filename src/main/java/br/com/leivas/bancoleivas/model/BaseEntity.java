package br.com.leivas.bancoleivas.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Version
    private Integer optmisticklock;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCreate;
    @Temporal(TemporalType.TIMESTAMP)
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