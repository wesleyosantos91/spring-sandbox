package io.wesleyosantos91.springsandbox.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record ResponseErro(
        @JsonProperty Integer status,
        @JsonProperty String erro,
        @JsonProperty String mensagem,
        @JsonProperty OffsetDateTime timestamp
) {
}
