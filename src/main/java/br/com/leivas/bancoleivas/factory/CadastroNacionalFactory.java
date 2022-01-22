package br.com.leivas.bancoleivas.factory;

import br.com.leivas.bancoleivas.dto.reg.CadastroNacionalDTO;
import br.com.leivas.bancoleivas.model.reg.CadastroNacional;
import br.com.leivas.bancoleivas.util.parser.CadastroNacionalParser;

public final class CadastroNacionalFactory implements IFactory<CadastroNacionalDTO, CadastroNacional> {
    @Override
    public CadastroNacional produce(CadastroNacionalDTO cadastroNacionalDTO) {
        return new CadastroNacionalParser().parse(cadastroNacionalDTO);
    }
}
