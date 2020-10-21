package io.wesleyosantos91.springsandbox.api;

import io.wesleyosantos91.springsandbox.model.request.RequestPostPessoa;
import io.wesleyosantos91.springsandbox.model.request.RequestPutPessoa;
import io.wesleyosantos91.springsandbox.model.response.ResponsePessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PessoaResourceAPI {

    ResponseEntity<List<ResponsePessoa>> lista();
    ResponseEntity<Page<ResponsePessoa>> pesquisa(Pageable pageable);
    ResponseEntity<ResponsePessoa> busca(Long codigo);
    ResponseEntity<ResponsePessoa> salva(RequestPostPessoa body);
    ResponseEntity<ResponsePessoa> altera(Long codigo, RequestPutPessoa body);
    ResponseEntity<Void> deleta(Long codigo);

}
