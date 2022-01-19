package br.com.leivas.bancoleivas.repository.fin;

import br.com.leivas.bancoleivas.model.fin.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
