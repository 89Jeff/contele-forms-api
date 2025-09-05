package com.contele.api.contele_forms_api.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data // Anotação do Lombok para gerar getters, setters, toString, etc.
public class RespostaTemplatesFormulariosContele {
    @JsonProperty("forms_templates") // Mantém o nome original do JSON
    private List<TemplateFormulario> templatesFormularios;

    @Data
    public static class TemplateFormulario {
        private String id;
        @JsonProperty("template_name")
        private String nomeTemplate;
        @JsonProperty("template_status")
        private String statusTemplate;
        @JsonProperty("template_public")
        private boolean templatePublico;
        @JsonProperty("created_by")
        private String criadoPor;
        @JsonProperty("created_at")
        private String criadoEm;
        @JsonProperty("last_update")
        private String ultimaAtualizacao;
        @JsonProperty("settings_flags")
        private List<String> flagsConfiguracao;
        private List<Segmento> segmentos;
        private List<Usuario> usuarios;
    }

    @Data
    public static class Segmento {
        private String id;
        private String tipo;
        private String titulo;
        @JsonProperty("is_required_to_answer")
        private boolean eRequisitoResponder;
    }

    @Data
    public static class Usuario {
        private String id;
        private String nome;
        private String email;
    }
}