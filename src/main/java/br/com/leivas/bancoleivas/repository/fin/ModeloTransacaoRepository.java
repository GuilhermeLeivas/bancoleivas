package br.com.leivas.bancoleivas.repository.fin;

import br.com.leivas.bancoleivas.dto.fin.TransacaoDTO;
import br.com.leivas.bancoleivas.model.fin.ModeloTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeloTransacaoRepository extends JpaRepository<ModeloTransacao, Long> {

    @Query("SELECT modelo FROM ModeloTransacao  modelo " +
            "WHERE modelo.codigo = ?1 " +
            "AND modelo.executacaoDaTransacao = ?2")
    Optional<ModeloTransacao> findByCodigo(Integer codigo, ModeloTransacao.ExecutacaoDaTransacao executacaoDaTransacao);
}
