package br.com.leivas.bancoleivas.util.parser;

import br.com.leivas.bancoleivas.dto.reg.CadastroNacionalDTO;
import br.com.leivas.bancoleivas.exception.custom.FalhaAoConverterCadastroNacional;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import br.com.leivas.bancoleivas.util.validator.CadastroNacionalValidator;

public class CadastroNacionalParser implements Parser<CadastroNacionalDTO, CadastroNacional> {
    @Override
    public CadastroNacional parse(CadastroNacionalDTO cadastroNacionalDTO) {
        try {
            Boolean documentoEhValido = new CadastroNacionalValidator().isValid(cadastroNacionalDTO.getNumero());
            if (documentoEhValido) {
                CadastroNacional.TipoCadastroNacional tipoCadastroNacional = cadastroNacionalDTO.getTipo();
                return CadastroNacional.builder()
                        .numero(cadastroNacionalDTO.getNumero())
                        .emissor(cadastroNacionalDTO.getEmissor())
                        .tipo(tipoCadastroNacional)
                        .build();
            }
        } catch (Exception ex) {
            throw new FalhaAoConverterCadastroNacional();
        }
        return null;
    }
}
