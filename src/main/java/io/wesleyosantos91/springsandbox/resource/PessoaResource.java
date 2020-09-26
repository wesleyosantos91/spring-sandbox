package io.wesleyosantos91.springsandbox.resource;

import io.wesleyosantos91.springsandbox.model.request.RequestPostPessoa;
import io.wesleyosantos91.springsandbox.model.response.ResponsePessoa;
import io.wesleyosantos91.springsandbox.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/pessoas")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaResource {

    private final PessoaService service;

    @GetMapping
    public ResponseEntity<List<ResponsePessoa>> lista() {

        return ResponseEntity.ok(service.lista());
    }

    @PostMapping
    public ResponseEntity<ResponsePessoa> salva(@RequestBody @Valid RequestPostPessoa body) {

        ResponsePessoa responsePessoa = service.salva(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePessoa);
    }
}
