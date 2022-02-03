package br.com.leivas.bancoleivas.repository.reg;

import br.com.leivas.bancoleivas.model.auth.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    @Query("SELECT permissao FROM Permissao permissao WHERE UPPER(permissao.descricao) = UPPER(?1)")
    Optional<Permissao> findByDescricao(String descricao);
}
