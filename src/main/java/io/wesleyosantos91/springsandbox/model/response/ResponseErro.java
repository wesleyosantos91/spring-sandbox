package io.wesleyosantos91.springsandbox.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.time.OffsetDateTime;

public record ResponseErro(
        @ApiModelProperty(example = "404")
        @JsonProperty Integer httpCode,

        @ApiModelProperty(example = "Not Found")
        @JsonProperty String httpMessage,


        @ApiModelProperty(example = "Not found pesssoa")
        @JsonProperty
        String erro,

        @ApiModelProperty(example = "Pessoa com código 1 não encontrado")
        @JsonProperty String mensagem,

        @ApiModelProperty(example = "2020-09-30T22:46:16.47643-03:00")
        @JsonProperty OffsetDateTime timestamp
) {
}
