package io.wesleyosantos91.springsandbox.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public record RequestPutPessoa(
        @ApiModelProperty(example = "Roberto Daniel André da Silva")
        @JsonProperty @NotEmpty(message = "Nome é obrigatorio") String nome,

        @ApiModelProperty(example = "1964-06-12")
        @JsonProperty("data_nascimento") @Past LocalDate dataNacimento,

        @ApiModelProperty(example = "robertodanielandredasilva_@oliveiracontabil.com.br")
        @JsonProperty @NotEmpty(message = "Email é obrigatorio") @Email String email
) {
    public RequestPutPessoa {
    }
}
