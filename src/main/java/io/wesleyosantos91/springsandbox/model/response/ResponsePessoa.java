package io.wesleyosantos91.springsandbox.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.wesleyosantos91.springsandbox.model.entity.Pessoa;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@AllArgsConstructor
public class ResponsePessoa extends RepresentationModel<ResponsePessoa> implements Serializable {

    @ApiModelProperty(example = "1")
    private Long codigo;

    @ApiModelProperty(example = "Roberto Daniel André da Silva")
    private String nome;

    @ApiModelProperty(example = "1964-06-12")
    @JsonProperty("data_nascimento")
    private LocalDate dataNacimento;

    @ApiModelProperty(example = "robertodanielandredasilva_@oliveiracontabil.com.br")
    private String email;

    @ApiModelProperty(example = "68103169811")
    private String cpf;

    public static ResponsePessoa toResponsePessoa(Pessoa pessoa) {
        return new ResponsePessoa(pessoa.getCodigo(), pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getEmail(), pessoa.getCpf());
    }
}
