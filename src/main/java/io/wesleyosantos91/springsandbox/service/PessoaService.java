package io.wesleyosantos91.springsandbox.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.wesleyosantos91.springsandbox.exception.core.FallbackMethodException;
import io.wesleyosantos91.springsandbox.exception.core.ObjectNotFoundException;
import io.wesleyosantos91.springsandbox.model.entity.Pessoa;
import io.wesleyosantos91.springsandbox.model.request.RequestPostPessoa;
import io.wesleyosantos91.springsandbox.model.request.RequestPutPessoa;
import io.wesleyosantos91.springsandbox.model.response.ResponsePessoa;
import io.wesleyosantos91.springsandbox.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    @HystrixCommand(fallbackMethod = "listaFallback")
    @Cacheable(value = "listaPessoas")
    public List<ResponsePessoa> lista() {
        return repository.findAll().stream().map(ResponsePessoa::toResponsePessoa).collect(Collectors.toList());
    }

    @Cacheable(value = "pesquisaPessoas")
    public Page<ResponsePessoa> pesquisa(Pageable pageable) {
        return repository.findAll(pageable).map(ResponsePessoa::toResponsePessoa);
    }

    public ResponsePessoa busca(Long codigo) {
        return ResponsePessoa.toResponsePessoa(buscaPessoa(codigo));

    }

    @Transactional
    @Caching(evict = { @CacheEvict(value = "listaPessoas", allEntries = true), @CacheEvict(value = "pesquisaPessoas", allEntries = true) })
    public ResponsePessoa salva(RequestPostPessoa body) {
        return ResponsePessoa.toResponsePessoa(repository.save(Pessoa.toPessoa(body)));
    }

    @Transactional
    @Caching(evict = { @CacheEvict(value = "listaPessoas", allEntries = true), @CacheEvict(value = "pesquisaPessoas", allEntries = true) })
    public ResponsePessoa altera(Long codigo, RequestPutPessoa body) {

        Pessoa pessoa = buscaPessoa(codigo);
        BeanUtils.copyProperties(body, pessoa, "codigo");
        return ResponsePessoa.toResponsePessoa(repository.save(pessoa));
    }

    @Caching(evict = { @CacheEvict(value = "listaPessoas", allEntries = true), @CacheEvict(value = "pesquisaPessoas", allEntries = true) })
    public void deleta(Long codigo) {
        repository.delete(buscaPessoa(codigo));
    }

    private Pessoa buscaPessoa(Long codigo) {
        return repository.findById(codigo)
                .orElseThrow(
                        () -> new ObjectNotFoundException(format("Pessoa com código %s não encontrado", codigo))
                );
    }

    public List<ResponsePessoa> listaFallback() {
        throw new FallbackMethodException("Serviço temporariamente indisponível");
    }
}
