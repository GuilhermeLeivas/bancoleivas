package br.com.leivas.bancoleivas.util.separator;

public class CadastroNacionalFilialSeparator implements Separator<String, String> {

    @Override
    public String separate(String numeroCompleto) {
        int numeroDigitos = 2;
        int numeroFilial = 4;
        int tamanhoBase = numeroCompleto.length();
        int comecoFilial = tamanhoBase - (numeroFilial + numeroDigitos);
        String filial = numeroCompleto.substring(comecoFilial, tamanhoBase - numeroDigitos);
        return filial;
    }
}
