package br.com.leivas.bancoleivas.config.security;

import br.com.leivas.bancoleivas.model.auth.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UsuarioSistema extends User {
    private final Usuario usuario;

    public UsuarioSistema(Usuario usuario,  GrantedAuthority authority) {
        super(usuario.getUsername(), usuario.getSenha(), List.of(authority));
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
