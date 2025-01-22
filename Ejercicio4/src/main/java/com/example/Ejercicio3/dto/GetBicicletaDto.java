package com.example.Ejercicio3.dto;

import com.example.Ejercicio3.model.Bicicleta;

public record GetBicicletaDto(
        Long id,
        String marca,
        String modelo,
        String estado,
        GetEstacionDto estacion
) {
    public static GetBicicletaDto of(Bicicleta bicicleta){
        return new GetBicicletaDto(
                bicicleta.getId(),
                bicicleta.getMarca(),
                bicicleta.getModelo(),
                bicicleta.getEstado(),
                GetEstacionDto.of(bicicleta.getEstacion())
                );
    }
}
