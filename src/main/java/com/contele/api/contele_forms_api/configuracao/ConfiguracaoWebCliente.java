package com.contele.api.contele_forms_api.configuracao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration // Indica que esta classe é uma fonte de beans de configuração.
public class ConfiguracaoWebCliente {

    @Value("${contele.api.base-url}") // Injeta a URL base do application.properties.
    private String urlBase;

    @Bean // Cria um bean (objeto gerenciado pelo Spring) do tipo WebClient.
    public WebClient clienteWebContele() {
        return WebClient.builder()
                .baseUrl(urlBase)
                .build();
    }
}
