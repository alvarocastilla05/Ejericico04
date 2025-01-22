package com.example.Ejercicio3.dto;

import com.example.Ejercicio3.model.Estacion;

public record GetEstacionDto(
        Long id,
        String nombre,
        String coordenadas,
        int capacidad
) {

    public static GetEstacionDto of(Estacion estacion){
        return new GetEstacionDto(
                estacion.getId(),
                estacion.getNombre(),
                estacion.getCoordenadas(),
                estacion.getCapacidad()
        );
    }
}
