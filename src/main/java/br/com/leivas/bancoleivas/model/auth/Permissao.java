package br.com.leivas.bancoleivas.model.auth;

import br.com.leivas.bancoleivas.dto.auth.PermissaoDTO;
import br.com.leivas.bancoleivas.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTHPERMISSAO")
@SequenceGenerator(name = "seqAuthPermissao", sequenceName = "SEQAUTHPERMISSAO", allocationSize = 1)
public class Permissao extends BaseEntity<PermissaoDTO, Permissao> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqAuthPermissao")
    private Long id;

    @Column
    private String descricao;

    public Long getId() {
        return id;
    }

    @Override
    public Permissao fromDTO(PermissaoDTO dto) {
        this.descricao = dto.getDescricao();
        return this;
    }
}