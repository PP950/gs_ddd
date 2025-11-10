package br.com.fiap.gs_ddd.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;

public record MatriculaDTO(

        Long id,

        @NotNull(message = "O ID do usuário é obrigatório")
        @Positive
        Long idUsuario,

        @NotNull(message = "O ID do curso é obrigatório")
        @Positive
        Long idCurso,

        @Size(max = 30)
        String status,

        @DecimalMin(value = "0.00", message = "A nota mínima é 0")
        @DecimalMax(value = "10.00", message = "A nota máxima é 10")
        Double nota
) { }
