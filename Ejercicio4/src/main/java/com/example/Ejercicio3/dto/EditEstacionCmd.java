package com.example.Ejercicio3.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EditEstacionCmd(
        @Min(1)
        Long numero,
        @NotBlank
        String nombre,
        @NotBlank
        String coordenadas,
        @Min(1)
        int capacidad
) {
}
