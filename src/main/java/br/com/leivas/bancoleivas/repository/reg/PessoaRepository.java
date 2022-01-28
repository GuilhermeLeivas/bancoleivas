package br.com.leivas.bancoleivas.repository.reg;

import br.com.leivas.bancoleivas.model.reg.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p INNER JOIN p.cadastroNacional cadNacional WHERE cadNacional.numero = ?1")
    Optional<Pessoa> findByCadastroNacional(String numero);
}
