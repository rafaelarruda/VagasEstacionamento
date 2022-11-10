package com.api.estacionamento.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// Validação dos Campos, para não haver conflito de informações
@Getter
@Setter
public class VagasEstacionamentoDto {

    @NotBlank
    private String numeroVaga;
    @NotBlank
    @Size(max = 7)
    private String placaCarro;
    @NotBlank
    private String marcaCarro;
    @NotBlank
    private String modeloCarro;
    @NotBlank
    private String corCarro;
    @NotBlank
    private String nomeResponsavelVeiculo;
    @NotBlank
    private String apartamento;
    @NotBlank
    private String bloco;

}
