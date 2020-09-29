package io.wesleyosantos91.springsandbox.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.wesleyosantos91.springsandbox.model.entity.Pessoa;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class ResponsePessoa extends RepresentationModel<ResponsePessoa> {

    private Long codigo;
    private String nome;
    @JsonProperty("data_nascimento")
    private LocalDate dataNacimento;
    private String email;
    private String cpf;

    public static ResponsePessoa toResponsePessoa(Pessoa pessoa) {
        return new ResponsePessoa(pessoa.getCodigo(), pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getEmail(), pessoa.getCpf());
    }
}
