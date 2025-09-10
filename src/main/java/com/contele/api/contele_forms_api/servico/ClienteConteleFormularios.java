package com.contele.api.contele_forms_api.servico;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.contele.api.contele_forms_api.modelo.RespostaFormulariosContele;
import com.contele.api.contele_forms_api.modelo.RespostaTemplatesFormulariosContele;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service // Marca esta classe como um serviço do Spring.
public class ClienteConteleFormularios {

    private WebClient clienteWeb;

    @Value("${contele.api.token}")
    private String tokenApi;

    @Value("${contele.api.base-url}")
    private String baseUrl;

    @PostConstruct
    public void init() {
        this.clienteWeb = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<RespostaTemplatesFormulariosContele> obterTemplatesFormularios() {
        return clienteWeb.get()
                .uri("/api/v1/list-form-templates")
                .header("Authorization", "Bearer " + tokenApi)
                .retrieve()
                .bodyToMono(RespostaTemplatesFormulariosContele.class);
    }

    public Mono<RespostaFormulariosContele> obterFormulariosPorVisita(String idDaVisita) {
        String linkedUrns = "v0:cge:task:" + idDaVisita;

        return clienteWeb.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/list-forms")
                        .queryParam("add_templates_information_to_form", true)
                        .queryParam("add_pois_information_to_form", true)
                        .queryParam("add_tasks_information_to_form", true)
                        .queryParam("add_users_information_to_form", true)
                        .queryParam("only_forms_with_answers", true)
                        .queryParam("linked_urns", linkedUrns)
                        .queryParam("page", 1)
                        .queryParam("per_page", 100)
                        .build())
                .header("Authorization", "Bearer " + tokenApi)
                .retrieve()
                .bodyToMono(RespostaFormulariosContele.class);
    }
}
