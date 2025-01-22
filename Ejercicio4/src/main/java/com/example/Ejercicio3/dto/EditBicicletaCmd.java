package com.example.Ejercicio3.dto;

import com.example.Ejercicio3.model.Estacion;

public record EditBicicletaCmd(
        String marca,
        String modelo,
        String estado,
        Long estacionId
) {
}
