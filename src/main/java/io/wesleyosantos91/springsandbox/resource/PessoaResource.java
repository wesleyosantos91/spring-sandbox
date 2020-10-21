package io.wesleyosantos91.springsandbox.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.wesleyosantos91.springsandbox.api.PessoaResourceAPI;
import io.wesleyosantos91.springsandbox.model.request.RequestPostPessoa;
import io.wesleyosantos91.springsandbox.model.request.RequestPutPessoa;
import io.wesleyosantos91.springsandbox.model.response.ResponseErro;
import io.wesleyosantos91.springsandbox.model.response.ResponsePessoa;
import io.wesleyosantos91.springsandbox.service.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(value = "Pessoas", description = "API com recursos da entidade pessoa", tags = {"PessoaResource"})
@RestController
@RequestMapping("/v1/pessoas")
public class PessoaResource implements PessoaResourceAPI {

    private final PessoaService service;

    public PessoaResource(PessoaService service) {
        this.service = service;
    }

    @ApiOperation(value = "Lista todos registro de pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponsePessoa.class, responseContainer = "List"),
            @ApiResponse(code = 503, message = "Service Unavailable", response = ResponseErro.class),
    })
    @GetMapping
    @Override
    public ResponseEntity<List<ResponsePessoa>> lista() {
        List<ResponsePessoa> pessoas = service.lista();
        pessoas.forEach(this::adicionarHateoas);
        return ResponseEntity.ok(pessoas);
    }

    @ApiOperation(value = "Pesquisar registro de pessoa paginado")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Pagina a ser carregada", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Quantidade de registros", defaultValue = "5"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Ordenacao dos registros")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponsePessoa.class, responseContainer = "Page"),
            @ApiResponse(code = 503, message = "Service Unavailable", response = ResponseErro.class),
    })
    @GetMapping("/pesquisa")
    @Override
    public ResponseEntity<Page<ResponsePessoa>> pesquisa(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) @ApiIgnore Pageable pageable) {
        Page<ResponsePessoa> pages = service.pesquisa(pageable);
        pages.forEach(this::adicionarHateoas);
        return ResponseEntity.ok(pages);
    }


    @ApiOperation(value = "Busca o registro de pessoa pelo código")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponsePessoa.class),
            @ApiResponse(code = 404, message = "Not Found", response = ResponseErro.class),
    })
    @GetMapping("/{codigo}")
    @Override
    public ResponseEntity<ResponsePessoa> busca(@ApiParam(value = "Código do registro pessoa", required = true, example = "1")
                                                    @PathVariable Long codigo) {
        ResponsePessoa pessoa = service.busca(codigo);
        adicionarHateoas(pessoa);
        return ResponseEntity.ok(pessoa);
    }


    @ApiOperation(value = "Salva um registro de pessoa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponsePessoa.class)
    })
    @PostMapping
    @Override
    public ResponseEntity<ResponsePessoa> salva(@RequestBody @Valid  RequestPostPessoa body) {
        ResponsePessoa pessoa = service.salva(body);
        adicionarHateoas(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }


    @ApiOperation(value = "Altera o registro de pessoa pelo código")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponsePessoa.class)
    })
    @PutMapping("/{codigo}")
    @Override
    public ResponseEntity<ResponsePessoa> altera(@ApiParam(value = "Código do registro pessoa", required = true, example = "1")
                                                     @PathVariable Long codigo, @RequestBody @Valid  RequestPutPessoa body) {
        ResponsePessoa pessoa = service.altera(codigo, body);
        adicionarHateoas(pessoa);
        return ResponseEntity.ok(pessoa);
    }

    @ApiOperation(value = "Deleta o registro de pessoa pelo código")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponsePessoa.class)
    })
    @DeleteMapping("/{codigo}")
    @Override
    public ResponseEntity<Void> deleta(@ApiParam(value = "Código do registro pessoa", required = true, example = "1")
                                           @PathVariable Long codigo) {
        service.deleta(codigo);
        return ResponseEntity.noContent().build();
    }

    private void adicionarHateoas(ResponsePessoa pessoa) {
        pessoa.add(linkTo(methodOn(PessoaResource.class).busca(pessoa.getCodigo())).withSelfRel());
    }
}
