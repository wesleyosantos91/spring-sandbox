package io.wesleyosantos91.springsandbox.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.wesleyosantos91.springsandbox.exception.core.FallbackMethodException;
import io.wesleyosantos91.springsandbox.exception.core.ObjectNotFoundException;
import io.wesleyosantos91.springsandbox.model.entity.Pessoa;
import io.wesleyosantos91.springsandbox.model.request.RequestPostPessoa;
import io.wesleyosantos91.springsandbox.model.request.RequestPutPessoa;
import io.wesleyosantos91.springsandbox.model.response.ResponsePessoa;
import io.wesleyosantos91.springsandbox.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {

    private final PessoaRepository repository;

    @HystrixCommand(fallbackMethod = "listaFallback")
    public List<ResponsePessoa> lista() {
        return repository.findAll().stream().map(ResponsePessoa::toResponsePessoa).collect(Collectors.toList());
    }

    public List<ResponsePessoa> listaFallback() {
        throw new FallbackMethodException("Serviço temporariamente indisponível");
    }

    public ResponsePessoa busca(Long codigo) {
        return ResponsePessoa.toResponsePessoa(buscaPessoa(codigo));

    }

    @Transactional
    public ResponsePessoa salva(RequestPostPessoa body) {
        return ResponsePessoa.toResponsePessoa(repository.save(Pessoa.toPessoa(body)));
    }

    @Transactional
    public ResponsePessoa altera(Long codigo, RequestPutPessoa body) {

        Pessoa pessoa = buscaPessoa(codigo);
        BeanUtils.copyProperties(body, pessoa, "codigo");
        return ResponsePessoa.toResponsePessoa(repository.save(pessoa));
    }

    public void deleta(Long codigo) {
        repository.delete(buscaPessoa(codigo));
    }

    private Pessoa buscaPessoa(Long codigo) {
        return repository.findById(codigo)
                .orElseThrow(
                        () -> new ObjectNotFoundException(format("Pessoa com código %s não encontrado", codigo))
                );
    }
}
