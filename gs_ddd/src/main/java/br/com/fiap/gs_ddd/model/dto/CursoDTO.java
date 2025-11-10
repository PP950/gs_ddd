package br.com.fiap.gs_ddd.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PositiveOrZero;

public record CursoDTO(

        Long id,

        @NotBlank(message = "O título é obrigatório")
        @Size(max = 200)
        String titulo,

        @Size(max = 1000)
        String descricao,

        @PositiveOrZero(message = "A carga horária deve ser positiva")
        Integer cargaHoraria
) { }
