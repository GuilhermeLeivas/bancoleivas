package br.com.leivas.bancoleivas.repository.fin;

import br.com.leivas.bancoleivas.dto.fin.ExtratoDTO;
import br.com.leivas.bancoleivas.model.fin.LancamentoExtrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoExtratoRepository extends JpaRepository<LancamentoExtrato, Long> {

    @Query("SELECT lancamentos FROM Conta conta " +
            "INNER JOIN conta.lancamentosExtrato lancamentos " +
            "WHERE conta.numero = :#{#pedidoExtrato.numeroConta} " +
            "AND (lancamentos.dtCreate >= :#{#pedidoExtrato.dtInicial} " +
            "AND lancamentos.dtCreate <= :#{#pedidoExtrato.dtFinal}) " +
            "ORDER BY lancamentos.dtCreate DESC")
    Page<LancamentoExtrato> lancamentosPorConta(@Param("pedidoExtrato") ExtratoDTO pedidoExtrato, Pageable pageable);
}
