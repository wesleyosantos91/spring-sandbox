package io.wesleyosantos91.springsandbox.resource;

import io.wesleyosantos91.springsandbox.model.request.RequestPostPessoa;
import io.wesleyosantos91.springsandbox.model.request.RequestPutPessoa;
import io.wesleyosantos91.springsandbox.model.response.ResponsePessoa;
import io.wesleyosantos91.springsandbox.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/pessoas")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaResource {

    private final PessoaService service;

    @GetMapping
    public ResponseEntity<List<ResponsePessoa>> lista() {

        List<ResponsePessoa> pessoas = service.lista();
        pessoas.forEach(this::adicionarHateoas);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ResponsePessoa> busca(@PathVariable Long codigo) {

        ResponsePessoa pessoa = service.busca(codigo);
        adicionarHateoas(pessoa);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<ResponsePessoa> salva(@RequestBody @Valid RequestPostPessoa body) {

        ResponsePessoa pessoa = service.salva(body);
        adicionarHateoas(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ResponsePessoa> altera(@PathVariable Long codigo, @RequestBody @Valid RequestPutPessoa body) {
        ResponsePessoa pessoa = service.altera(codigo, body);
        adicionarHateoas(pessoa);
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleta(@PathVariable Long codigo) {
        service.deleta(codigo);
        return ResponseEntity.noContent().build();
    }

    private void adicionarHateoas(ResponsePessoa pessoa) {
        pessoa.add(linkTo(methodOn(PessoaResource.class).busca(pessoa.getCodigo())).withSelfRel());
    }
}
