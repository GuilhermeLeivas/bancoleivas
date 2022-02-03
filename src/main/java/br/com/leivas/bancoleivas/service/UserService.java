package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.config.security.UsuarioSistema;
import br.com.leivas.bancoleivas.exception.custom.CredenciasIncorretas;
import br.com.leivas.bancoleivas.model.auth.Usuario;
import br.com.leivas.bancoleivas.repository.reg.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioCandidato = this.usuarioRepository.findByUsername(username);
        if (usuarioCandidato.isEmpty()) {
            throw new CredenciasIncorretas("As credencias informadas estão incorretas!");
        }
        Usuario usuario = usuarioCandidato.get();
        return new UsuarioSistema(usuario, new SimpleGrantedAuthority(usuario.getPermissao().getDescricao().toUpperCase(Locale.ROOT)));
    }
}
