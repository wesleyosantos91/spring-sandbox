package io.wesleyosantos91.springsandbox.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.wesleyosantos91.springsandbox.model.entity.Pessoa;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public record RequestPostPessoa(
        @JsonProperty @NotEmpty(message = "Nome é obrigatorio") String nome,
        @JsonProperty("data_nascimento") @Past  LocalDate dataNacimento,
        @JsonProperty @NotEmpty(message = "Email é obrigatorio") @Email String email,
        @JsonProperty @NotEmpty(message = "CPF é obrigatorio") String cpf) {

    public RequestPostPessoa { }
}
