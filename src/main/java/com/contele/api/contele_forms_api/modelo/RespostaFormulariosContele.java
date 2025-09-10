package com.contele.api.contele_forms_api.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data // Anotação do Lombok para gerar código repetitivo.
public class RespostaFormulariosContele {
    @JsonProperty("forms")
    private List<Formulario> formularios;
    
    @JsonProperty("pagination")
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
        
        @JsonProperty("answers")
        private List<Object> respostas;
    }

    @Data
    public static class Paginacao {
        @JsonProperty("page")
        private int pagina;
        
        @JsonProperty("per_page")
        private int porPagina;
        
        @JsonProperty("total_pages")
        private int totalPaginas;
        
        @JsonProperty("total_items")
        private int totalItens;
    }
}