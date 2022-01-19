package br.com.leivas.bancoleivas.repository.reg;

import br.com.leivas.bancoleivas.model.reg.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
