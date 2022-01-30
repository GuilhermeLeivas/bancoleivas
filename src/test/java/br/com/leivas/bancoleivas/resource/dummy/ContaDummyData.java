package br.com.leivas.bancoleivas.resource.dummy;

import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.model.reg.NumeroConta;

public class ContaDummyData {

    public static ContaDTO getDTOA() {
        return new ContaDTO(PessoaDummyData.pessoaFisicaDTO("Guilherme Leivas"));
    }

    public static ContaDTO getDTOB() {
        return new ContaDTO(PessoaDummyData.pessoaJuridicaDTO("Susi De F Leivas"));
    }

    public static Conta getEntityA(Long fakeNumeroConta) {
        ContaDTO dto = getDTOA();
        Conta conta = new Conta().fromDTO(dto);
        conta.adicionaNumeroConta(new NumeroConta(fakeNumeroConta));
        conta.setId(1L);
        return conta;
    }

    public static Conta getEntityB(Long fakeNumeroConta) {
        ContaDTO dto = getDTOB();
        Conta conta = new Conta().fromDTO(dto);
        conta.adicionaNumeroConta(new NumeroConta(fakeNumeroConta));
        conta.setId(2L);
        return conta;
    }
}
