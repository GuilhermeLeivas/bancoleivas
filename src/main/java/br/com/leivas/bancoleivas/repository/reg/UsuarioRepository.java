package br.com.leivas.bancoleivas.repository.reg;

import br.com.leivas.bancoleivas.model.auth.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT usuario FROM Conta conta  " +
            "INNER JOIN conta.usuario usuario " +
            "WHERE conta.numero = ?1")
    Optional<Usuario> findUsuarioByNumeroConta(Long numeroConta);
}
