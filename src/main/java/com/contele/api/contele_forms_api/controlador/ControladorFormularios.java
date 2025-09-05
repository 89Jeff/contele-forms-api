package com.contele.api.contele_forms_api.controlador;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/templates") // Mapeia requisições GET para /contele/templates.
    public Mono<ResponseEntity<RespostaTemplatesFormulariosContele>> obterTemplates() {
        return clienteForms.obterTemplatesFormularios()
                .map(ResponseEntity::ok) // Em caso de sucesso, retorna uma resposta HTTP 200.
                .defaultIfEmpty(ResponseEntity.notFound().build()); // Se a resposta for vazia, retorna 404.
    }

    @GetMapping("/forms") // Mapeia requisições GET para /contele/forms.
    public Mono<ResponseEntity<RespostaFormulariosContele>> obterFormularios(@RequestParam String idsTemplates) {
        return clienteForms.obterFormulariosPorTemplates(idsTemplates)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
