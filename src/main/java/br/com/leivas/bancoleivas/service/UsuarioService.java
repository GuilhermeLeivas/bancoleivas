package br.com.leivas.bancoleivas.service;

import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.model.auth.Usuario;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.repository.reg.PermissaoRepository;
import br.com.leivas.bancoleivas.repository.reg.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PermissaoRepository permissaoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PermissaoRepository permissaoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.permissaoRepository = permissaoRepository;
    }

    public Conta criaUsuarioConta(Conta conta, PessoaDTO pessoa) {
        Usuario usuario = new Usuario();
        usuario.setPermissao(this.permissaoRepository.findByDescricao("CLIENTE").get());
        usuario.setIdentificacao(pessoa.nomeReferencia());
        usuario.setUsername(this.montaUsername(pessoa.nomeReferencia(), conta));
        usuario.setSenha(pessoa.getDetalhesUsuario().getSenhaDesejada());
        usuario = this.usuarioRepository.save(usuario);
        conta.setUsuario(usuario);
        return conta;
    }

    private String montaUsername(String nome, Conta conta) {
        return nome.split(" ")[0].trim() + conta.getNumero();
    }
}
