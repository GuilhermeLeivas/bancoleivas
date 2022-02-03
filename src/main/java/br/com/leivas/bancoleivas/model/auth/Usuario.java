package br.com.leivas.bancoleivas.model.auth;

import br.com.leivas.bancoleivas.dto.auth.UsuarioDTO;
import br.com.leivas.bancoleivas.model.BaseEntity;
import br.com.leivas.bancoleivas.model.reg.Conta;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTHUSUARIO")
@SequenceGenerator(name = "seqAuthUsuario", sequenceName = "SEQAUTHUSUARIO", allocationSize = 1)
public class Usuario extends BaseEntity<UsuarioDTO, Usuario> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqAuthUsuario")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTAID", referencedColumnName = "ID")
    private Conta conta;
    @Column
    private String senha;
    @ManyToOne
    @JoinColumn(name = "PERMISSAOID", referencedColumnName = "ID")
    private Permissao permissao;

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    @Override
    public Usuario fromDTO(UsuarioDTO dto) {
        return null;
    }
}
