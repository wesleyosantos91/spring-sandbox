package io.wesleyosantos91.springsandbox.model.entity;

import io.wesleyosantos91.springsandbox.model.request.RequestPostPessoa;
import io.wesleyosantos91.springsandbox.model.request.RequestPutPessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    public static Pessoa toPessoa(RequestPostPessoa body) {
        return Pessoa.builder()
                .nome(body.nome())
                .dataNascimento(body.dataNacimento())
                .cpf(body.cpf())
                .email(body.email())
                .build();
    }

    public static Pessoa toPessoa(RequestPutPessoa body) {
        return Pessoa.builder()
                .nome(body.nome())
                .dataNascimento(body.dataNacimento())
                .email(body.email())
                .build();
    }
}
