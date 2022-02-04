package br.com.leivas.bancoleivas;

import br.com.leivas.bancoleivas.config.propertie.BancoLeivasProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BancoLeivasProperties.class)
public class BancoleivasApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancoleivasApplication.class, args);
    }

}
