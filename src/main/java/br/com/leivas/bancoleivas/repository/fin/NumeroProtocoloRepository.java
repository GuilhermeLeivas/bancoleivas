package br.com.leivas.bancoleivas.repository.fin;

import br.com.leivas.bancoleivas.model.fin.NumeroProtocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumeroProtocoloRepository extends JpaRepository<NumeroProtocolo, Long> {
}
