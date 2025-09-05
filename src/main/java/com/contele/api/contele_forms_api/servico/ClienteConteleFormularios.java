package com.contele.api.contele_forms_api.servico;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.contele.api.contele_forms_api.modelo.RespostaFormulariosContele;
import com.contele.api.contele_forms_api.modelo.RespostaTemplatesFormulariosContele;

import reactor.core.publisher.Mono;

@Service // Marca esta classe como um serviço do Spring.
public class ClienteConteleFormularios {

    private final WebClient clienteWeb;

    @Value("${contele.api.token}") // Injeta o token de autenticação.
    private String tokenApi;

    @Value("${contele.api.key}") // Injeta a chave de API.
    private String chaveApi;

    public ClienteConteleFormularios(WebClient clienteWebContele) {
        // O Spring injeta o bean WebClient configurado.
        this.clienteWeb = clienteWebContele;
    }

    public Mono<RespostaTemplatesFormulariosContele> obterTemplatesFormularios() {
        return clienteWeb.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/list-forms-templates")
                        .queryParam("page", 1)
                        .queryParam("per_page", 50)
                        .queryParam("status", "active")
                        .queryParam("add_users_information_to_form_template", true)
                        .build())
                .header("Authorization", "Bearer " + tokenApi) // Adiciona o cabeçalho de token JWT.
                .header("X-API-KEY", chaveApi) // Adiciona o cabeçalho da chave de API.
                .retrieve()
                .bodyToMono(RespostaTemplatesFormulariosContele.class); // Mapeia a resposta para a classe Java.
    }

    public Mono<RespostaFormulariosContele> obterFormulariosPorTemplates(String idsTemplates) {
        return clienteWeb.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/list-forms")
                        .queryParam("add_templates_information_to_form", true)
                        .queryParam("add_pois_information_to_form", true)
                        .queryParam("add_tasks_information_to_form", true)
                        .queryParam("add_users_information_to_form", true)
                        .queryParam("only_forms_with_answers", true)
                        .queryParam("templates_ids", idsTemplates)
                        .queryParam("page", 1)
                        .queryParam("per_page", 100)
                        .build())
                .header("Authorization", "Bearer " + tokenApi)
                .header("X-API-KEY", chaveApi)
                .retrieve()
                .bodyToMono(RespostaFormulariosContele.class);
    }
}
