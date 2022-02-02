package br.com.leivas.bancoleivas.config.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwsEncoder;
import org.springframework.security.oauth2.server.authorization.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Duration;
import java.util.UUID;

@Configuration
public class AuthorizationServerConfig {

    private final PasswordEncoder passwordEncoder;

    public AuthorizationServerConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean // Oauth2 Clients configuration
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient testeClient = RegisteredClient
                .withId(UUID.randomUUID().toString())
                .clientId("teste")
                .clientSecret(this.passwordEncoder.encode("teste123"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("https://oidcdebugger.com/debug") // Somente para testes
                .scope("read")
                .scope("write")
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(30))
                        .refreshTokenTimeToLive(Duration.ofDays(24))
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(false)
                        .build())
                .build();
        return new InMemoryRegisteredClientRepository(testeClient);
    }

    @Bean // AuthServer filterChain
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        return http.formLogin(Customizer.withDefaults()).build();
    }

    @Bean // Jwt customization
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return (context) -> {
            UsernamePasswordAuthenticationToken authenticationToken = context.getPrincipal();
            UsuarioSistema usuario = (UsuarioSistema) authenticationToken.getPrincipal();
            context.getClaims().claim("nome", usuario.getUsuario().getConta().getPessoa().nomeReferencia());
            context.getClaims().claim("authorities", usuario.getUsuario().getPermissao().getDescricaoUpperCase());
        };
    }

    @Bean // Creates RSAKey that is responsible for JWT signature.
    public JWKSet signatureRSAKey() throws JOSEException {
        RSAKey rsaKey = new RSAKeyGenerator(2048)
                .keyID(UUID.randomUUID().toString())
                .keyUse(KeyUse.SIGNATURE)
                .generate();
        return new JWKSet(rsaKey);
    }

    @Bean // Telling spring wich RSAKEY to use. We define this RSA in @signatureRSAKey
    public JWKSource<SecurityContext> jwkSource(JWKSet signatureRSAKey) {
        return (jwkSelector, securityContext) -> jwkSelector.select(signatureRSAKey);
    }

    @Bean // After the signature, we encode the JWT
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwsEncoder(jwkSource);
    }

    @Bean
    // Here we are telling who is the issuer of JWT, wich is in our case: http://localhost:8080; This information is algo inside JWT
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder()
                .issuer("http://localhost:8080")
                .build();
    }
}
