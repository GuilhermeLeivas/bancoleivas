package br.com.leivas.bancoleivas.config.security;

public class SecurityWhiteList {

    public static final String[] AUTH_WHITELIST = {
            "**/swagger-resources/**",
            "**/swagger-ui.html",
            "**/v2/api-docs",
            "**/webjars/**",
            "**/swagger-ui/index.html",
    };
}
