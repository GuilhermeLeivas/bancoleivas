package br.com.leivas.bancoleivas.repository.fin;

import br.com.leivas.bancoleivas.model.fin.ModeloTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeloTransacaoRepository extends JpaRepository<ModeloTransacao, Long> {

    Optional<ModeloTransacao> findByCodigo(Integer codigo);
}
