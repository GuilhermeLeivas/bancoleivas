package br.com.leivas.bancoleivas.config.propertie;

public class AppKeyStore {

    private String rsaFile;
    private String rsaAlias;
    private String rsaPassword;

    public String getRsaFile() {
        return rsaFile;
    }

    public void setRsaFile(String rsaFile) {
        this.rsaFile = rsaFile;
    }

    public String getRsaAlias() {
        return rsaAlias;
    }

    public void setRsaAlias(String rsaAlias) {
        this.rsaAlias = rsaAlias;
    }

    public String getRsaPassword() {
        return rsaPassword;
    }

    public void setRsaPassword(String rsaPassword) {
        this.rsaPassword = rsaPassword;
    }
}
