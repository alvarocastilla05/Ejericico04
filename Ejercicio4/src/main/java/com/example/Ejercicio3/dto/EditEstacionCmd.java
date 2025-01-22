package com.example.Ejercicio3.dto;

public record EditEstacionCmd(
        Long numero,
        String nombre,
        String coordenadas,
        int capacidad
) {
}
