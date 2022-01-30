package br.com.leivas.bancoleivas.resource.dummy;

import br.com.leivas.bancoleivas.dto.reg.CadastroNacionalDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaFisicaDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaJuridicaDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import br.com.leivas.bancoleivas.model.reg.Pessoa;
import br.com.leivas.bancoleivas.model.reg.PessoaFisica;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class PessoaDummyData {

    public static PessoaDTO pessoaFisicaDTO(String nome) {
        LocalDate dataNascimento = LocalDate.of(1998, 10, 2);
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
        pessoaFisicaDTO.setNome(nome);
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
        return pessoaFisicaDTO;
    }

    public static PessoaDTO pessoaJuridicaDTO(String nome) {
        LocalDate dataFundacao = LocalDate.of(1968, 5, 12);
        PessoaJuridicaDTO pessoaFisicaDTO = new PessoaJuridicaDTO();
        pessoaFisicaDTO.setNomeFantasia(nome);
        pessoaFisicaDTO.setDataFundacao(Date.from(dataFundacao.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        pessoaFisicaDTO.setInscEstadual("23234");
        pessoaFisicaDTO.setInscMunicipal("12134");
        pessoaFisicaDTO.setCadastroNacional(
                CadastroNacionalDTO
                        .builder()
                        .emissor("ssp")
                        .numero("20139621000168")
                        .tipo(CadastroNacional.TipoCadastroNacional.CNPJ)
                        .build()
        );
        return pessoaFisicaDTO;
    }

    public static Pessoa pessoaFisica() {
        PessoaDTO pessoaDTO = pessoaFisicaDTO("Guilherme Leivas");
        return new PessoaFisica().fromDTO(pessoaDTO);
    }

    public static Pessoa pessoaFisicaFromDTO(PessoaDTO pessoaDTO) {
        return new PessoaFisica().fromDTO(pessoaDTO);
    }
}