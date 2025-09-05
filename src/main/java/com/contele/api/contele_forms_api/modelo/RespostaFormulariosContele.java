package com.contele.api.contele_forms_api.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data // Anotação do Lombok para gerar código repetitivo.
public class RespostaFormulariosContele {
    private List<Formulario> formularios;
    private Paginacao paginacao;

    @Data
    public static class Formulario {
        @JsonProperty("form_id")
        private String idFormulario;
        @JsonProperty("template_information")
        private Object informacaoTemplate;
        @JsonProperty("pois_information")
        private Object informacaoPois;
        @JsonProperty("tasks_information")
        private Object informacaoTarefas;
        @JsonProperty("users_information")
        private Object informacaoUsuarios;
        private List<Object> respostas;
    }

    @Data
    public static class Paginacao {
        private int pagina;
        @JsonProperty("per_page")
        private int porPagina;
        @JsonProperty("total_pages")
        private int totalPaginas;
        @JsonProperty("total_items")
        private int totalItens;
    }
}