package br.com.leivas.bancoleivas.repository.fin;

import br.com.leivas.bancoleivas.dto.fin.ExtratoDTO;
import br.com.leivas.bancoleivas.model.fin.Extrato;
import br.com.leivas.bancoleivas.model.fin.LancamentoExtrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoExtratoRepository extends JpaRepository<LancamentoExtrato, Long> {

    @Query("SELECT lancamento FROM LancamentoExtrato lancamento " +
            "INNER JOIN lancamento.conta conta " +
            "WHERE conta.numero = :#{#pedidoExtrato.numeroConta} " +
            "AND (lancamento.dtCreate BETWEEN :#{#pedidoExtrato.dtInicial} AND :#{#pedidoExtrato.dtFinal}) " +
            "ORDER BY lancamento.dtCreate DESC")
    Page<LancamentoExtrato> lancamentosPorConta(@Param("pedidoExtrato") ExtratoDTO pedidoExtrato, Pageable pageable);
}
