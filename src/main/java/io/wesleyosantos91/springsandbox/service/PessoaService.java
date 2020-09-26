package io.wesleyosantos91.springsandbox.service;

import io.wesleyosantos91.springsandbox.model.entity.Pessoa;
import io.wesleyosantos91.springsandbox.model.request.RequestPostPessoa;
import io.wesleyosantos91.springsandbox.model.response.ResponsePessoa;
import io.wesleyosantos91.springsandbox.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {

    private final PessoaRepository repository;

    public List<ResponsePessoa> lista() {
        return repository.findAll().stream().map(ResponsePessoa::toResponsePessoa).collect(Collectors.toList());
    }

    @Transactional
    public ResponsePessoa salva(RequestPostPessoa body) {

        Pessoa pessoa = repository.save(Pessoa.toPessoa(body));

        return ResponsePessoa.toResponsePessoa(pessoa);
    }
}
