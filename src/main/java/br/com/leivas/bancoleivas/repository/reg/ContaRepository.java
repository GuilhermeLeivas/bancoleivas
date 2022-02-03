package br.com.leivas.bancoleivas.repository.reg;

import br.com.leivas.bancoleivas.model.auth.Usuario;
import br.com.leivas.bancoleivas.model.reg.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("SELECT conta FROM Conta conta WHERE conta.numero = ?1")
    Optional<Conta> findByNumero(Long numeroConta);

    @Query("SELECT conta FROM Conta conta " +
            "INNER JOIN conta.usuario usuario " +
            "WHERE usuario.id = :#{#usuario.id}")
    Optional<Conta> contaByUsuario(Usuario usuario);
}
