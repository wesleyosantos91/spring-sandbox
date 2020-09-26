package io.wesleyosantos91.springsandbox.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.wesleyosantos91.springsandbox.model.entity.Pessoa;

import java.time.LocalDate;

public record ResponsePessoa(
        @JsonProperty Long Codigo,
        @JsonProperty String nome,
        @JsonProperty("data_nascimento") LocalDate dataNacimento,
        @JsonProperty String email,
        @JsonProperty String cpf
) {
    public static ResponsePessoa toResponsePessoa(Pessoa pessoa) {
        return new ResponsePessoa(pessoa.getCodigo(), pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getEmail(), pessoa.getCpf());
    }
}
