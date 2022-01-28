package br.com.leivas.bancoleivas.repository.reg;

import br.com.leivas.bancoleivas.model.reg.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByNumero(Long numeroConta);
}
