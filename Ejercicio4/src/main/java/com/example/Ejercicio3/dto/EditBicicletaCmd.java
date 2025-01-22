package com.example.Ejercicio3.dto;

public record EditBicicletaCmd(
        String marca,
        String modelo,
        String estado,
        Long estacionId
) {
}
