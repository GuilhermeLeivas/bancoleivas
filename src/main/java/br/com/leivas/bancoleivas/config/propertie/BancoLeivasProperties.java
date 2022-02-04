package br.com.leivas.bancoleivas.config.propertie;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bancoleivas")
public class BancoLeivasProperties {
    private AppResources resource;

    public AppResources getResource() {
        return resource;
    }

    public void setResource(AppResources resource) {
        this.resource = resource;
    }
}
