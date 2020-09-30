package io.wesleyosantos91.springsandbox.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public record RequestPutPessoa(
        @JsonProperty @NotEmpty(message = "Nome é obrigatorio") String nome,
        @JsonProperty("data_nascimento") @Past LocalDate dataNacimento,
        @JsonProperty @NotEmpty(message = "Email é obrigatorio") @Email String email
) {
    public RequestPutPessoa {
    }
}