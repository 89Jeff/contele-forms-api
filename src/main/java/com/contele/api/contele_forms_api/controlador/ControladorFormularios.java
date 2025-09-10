package com.contele.api.contele_forms_api.controlador;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.contele.api.contele_forms_api.modelo.RespostaFormulariosContele;
import com.contele.api.contele_forms_api.modelo.RespostaTemplatesFormulariosContele;
import com.contele.api.contele_forms_api.servico.ClienteConteleFormularios;
import reactor.core.publisher.Mono;

@RestController // Indica que esta classe é um controlador REST.
@RequestMapping("/contele") // Define o caminho base para todos os endpoints.
public class ControladorFormularios {

    private final ClienteConteleFormularios clienteForms;

    public ControladorFormularios(ClienteConteleFormularios clienteConteleForms) {
        this.clienteForms = clienteConteleForms;
    }

    @GetMapping("/templates")
    public Mono<ResponseEntity<RespostaTemplatesFormulariosContele>> obterTemplates() {
        return clienteForms.obterTemplatesFormularios()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Novo endpoint mais simples
    @GetMapping("/forms/{idDaVisita}")
    public Mono<ResponseEntity<RespostaFormulariosContele>> obterFormulariosPorVisita(
            @PathVariable String idDaVisita) {
        
        return clienteForms.obterFormulariosPorVisita(idDaVisita)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
