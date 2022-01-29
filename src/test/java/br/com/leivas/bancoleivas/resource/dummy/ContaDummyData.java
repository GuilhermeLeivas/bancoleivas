package br.com.leivas.bancoleivas.resource.dummy;

import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.model.reg.NumeroConta;

public class ContaDummyData {

    public static ContaDTO getDTO() {
        return new ContaDTO(PessoaDummyData.pessoaFisicaDTO("Guilherme Leivas"));
    }

    public static Conta getEntity(Long fakeNumeroConta) {
        ContaDTO dto = getDTO();
        Conta conta = new Conta().fromDTO(dto);
        conta.adicionaNumeroConta(new NumeroConta(fakeNumeroConta));
        conta.setId(1L);
        return conta;
    }
}
