package br.com.leivas.bancoleivas.resource.dummy;

import br.com.leivas.bancoleivas.dto.reg.CadastroNacionalDTO;
import br.com.leivas.bancoleivas.dto.reg.ContaDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaFisicaDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import br.com.leivas.bancoleivas.model.reg.Conta;
import br.com.leivas.bancoleivas.model.reg.NumeroConta;
import br.com.leivas.bancoleivas.model.reg.PessoaFisica;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class ContaDummyData {

    public static ContaDTO getDTO() {
        LocalDate dataNascimento = LocalDate.of(1998, 10, 2);
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
        pessoaFisicaDTO.setNome("Guilherme Leivas");
        pessoaFisicaDTO.setDataNascimento(Date.from(dataNascimento.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        pessoaFisicaDTO.setNomePai("Carlos Alberto");
        pessoaFisicaDTO.setNomeMae("Susi Leivas");
        pessoaFisicaDTO.setNomeConjugue(null);
        pessoaFisicaDTO.setSexo(PessoaFisica.SexoPessoa.MASCULINO);
        pessoaFisicaDTO.setCadastroNacional(
                CadastroNacionalDTO
                        .builder()
                        .emissor("ssp")
                        .numero("76626415034")
                        .tipo(CadastroNacional.TipoCadastroNacional.CPF)
                        .build()
        );
        return new ContaDTO(pessoaFisicaDTO);
    }

    public static Conta getEntity(Long fakeNumeroConta) {
        ContaDTO dto = getDTO();
        Conta conta = new Conta().fromDTO(dto);
        conta.adicionaNumeroConta(new NumeroConta(fakeNumeroConta));
        return conta;
    }
}
