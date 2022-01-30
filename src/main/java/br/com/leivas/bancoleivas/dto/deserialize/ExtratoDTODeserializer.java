package br.com.leivas.bancoleivas.dto.deserialize;

import br.com.leivas.bancoleivas.dto.fin.ExtratoDTO;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ExtratoDTODeserializer extends StdDeserializer<ExtratoDTO> {

    public ExtratoDTODeserializer() {
        this((JavaType) null);
    }

    public ExtratoDTODeserializer(Class<?> vc) {
        super(vc);
    }

    protected ExtratoDTODeserializer(JavaType valueType) {
        super(valueType);
    }

    protected ExtratoDTODeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @SneakyThrows
    @Override
    public ExtratoDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        Date dtInicial;
        Date dtFinal;
        String dtInicialText = jsonNode.get("dtInicial").asText();
        String dtFinalText = jsonNode.get("dtFinal").asText();
        dtInicial = sdf.parse(dtInicialText);
        dtFinal = sdf.parse(dtFinalText);
        dtFinal = this.mesmaDataNaRequisicao(dtInicialText, dtFinalText) ? this.dataProximoDia(dtFinal) : dtFinal;
        ExtratoDTO.builder()
                .numeroConta(Long.valueOf(jsonNode.get("numeroConta").asText()))
                .dtInicial(dtInicial)
                .dtFinal(dtFinal)
                .build();
        return null;
    }

    private boolean mesmaDataNaRequisicao(String data1, String data2) {
        return data1.equals(data2);
    }

    private Date dataProximoDia(Date date) {
        LocalDateTime proximoDia = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        proximoDia = proximoDia.plusDays(1);
        date = Date.from(proximoDia.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }
}
