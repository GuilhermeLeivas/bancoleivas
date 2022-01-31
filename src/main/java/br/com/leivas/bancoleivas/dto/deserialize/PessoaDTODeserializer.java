package br.com.leivas.bancoleivas.dto.deserialize;

import br.com.leivas.bancoleivas.dto.reg.CadastroNacionalDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaFisicaDTO;
import br.com.leivas.bancoleivas.dto.reg.PessoaJuridicaDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import br.com.leivas.bancoleivas.model.reg.PessoaFisica;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.leivas.bancoleivas.model.reg.CadastroNacional.TipoCadastroNacional.CPF;

public class PessoaDTODeserializer extends StdDeserializer<PessoaDTO> {

    private PessoaDTO pessoaDTO;
    private JsonNode jsonNode;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PessoaDTODeserializer() {
        this((JavaType) null);
    }

    public PessoaDTODeserializer(Class<?> vc) {
        super(vc);
    }

    protected PessoaDTODeserializer(JavaType valueType) {
        super(valueType);
    }

    protected PessoaDTODeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public PessoaDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        this.jsonNode = jsonParser.getCodec().readTree(jsonParser);
        CadastroNacionalDTO cadastroNacionalDTO = this.fillCadastroNacionalDTO();
        try {
            this.pessoaDTO = cadastroNacionalDTO.getTipo() == CPF ? this.fillPessoaFisicaDTO() : this.fillPessoaJuridicaDTO();
            this.pessoaDTO.setCadastroNacional(cadastroNacionalDTO);
        } catch (ParseException ex) {
            Logger.getLogger(getClass().getSimpleName()).log(Level.SEVERE, "Falha ao deserializar PessoaDTO {0}", ex);
        }
        return this.pessoaDTO;
    }

    private PessoaDTO fillPessoaFisicaDTO() throws ParseException {
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
        pessoaFisicaDTO.setNome(this.jsonNode.get("nome") != null ? this.jsonNode.get("nome").asText() : null);
        pessoaFisicaDTO.setDataNascimento(this.jsonNode.get("dataNascimento") != null ?
                this.sdf.parse(this.jsonNode.get("dataNascimento").asText()) : null);
        pessoaFisicaDTO.setNomePai(this.jsonNode.get("nomePai") != null ? this.jsonNode.get("nomePai").asText() : null);
        pessoaFisicaDTO.setNomeMae(this.jsonNode.get("nomeMae") != null ? this.jsonNode.get("nomeMae").asText() : null);
        pessoaFisicaDTO.setNomeConjugue(this.jsonNode.get("nomeConjugue") != null ? this.jsonNode.get("nomeConjugue").asText() : null);
        pessoaFisicaDTO.setSexo(this.jsonNode.get("sexo") != null ? PessoaFisica.SexoPessoa.valueOf(this.jsonNode.get("sexo").asText()) : null);
        return pessoaFisicaDTO;
    }

    private PessoaDTO fillPessoaJuridicaDTO() throws ParseException {
        PessoaJuridicaDTO pessoaJuridicaDTO = new PessoaJuridicaDTO();
        pessoaJuridicaDTO.setInscEstadual(this.jsonNode.get("inscEstadual") != null ? this.jsonNode.get("inscEstadual").asText() : null);
        pessoaJuridicaDTO.setInscMunicipal(this.jsonNode.get("inscMunicipal") != null ? this.jsonNode.get("inscMunicipal").asText() : null);
        pessoaJuridicaDTO.setNomeFantasia(this.jsonNode.get("nomeFantasia") != null ? this.jsonNode.get("nomeFantasia").asText() : null);
        pessoaJuridicaDTO.setDataFundacao(this.jsonNode.get("dataFundacao") != null ? this.sdf.parse(this.jsonNode.get("dataFundacao").asText()) : null);
        return pessoaJuridicaDTO;
    }

    private CadastroNacionalDTO fillCadastroNacionalDTO() {
        CadastroNacionalDTO cadastroNacionalDTO = new CadastroNacionalDTO();
        if (this.jsonNode.get("cadastroNacional") != null) {
            cadastroNacionalDTO.setTipo(this.jsonNode.get("cadastroNacional").get("tipo") != null ?
                    CadastroNacional.TipoCadastroNacional.valueOf(this.jsonNode.get("cadastroNacional").get("tipo").asText()) : null);
            cadastroNacionalDTO.setNumero(this.jsonNode.get("cadastroNacional").get("numero") != null ?
                    this.jsonNode.get("cadastroNacional").get("numero").asText() : null);
            cadastroNacionalDTO.setEmissor(this.jsonNode.get("cadastroNacional").get("emissor") != null ?
                    this.jsonNode.get("cadastroNacional").get("emissor").asText() : null);
        }
        return cadastroNacionalDTO;
    }
}
