package com.example.Ejercicio3.service;

import com.example.Ejercicio3.dto.EditEstacionCmd;
import com.example.Ejercicio3.model.Estacion;
import com.example.Ejercicio3.repos.EstacionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstacionService {

    private final EstacionRepository estacionRepository;

    public List<Estacion> findAll(){
        List<Estacion> result = estacionRepository.findAll();

        if(result.isEmpty())
            throw new EntityNotFoundException("No se encontraron estaciones con esos criterios de búsqueda.");
        return result;
    }

    public Estacion findById(Long id){
        return estacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No hay estaciones con el id: "+ id +"."));
    }

    public Estacion save(EditEstacionCmd nuevo){
        return estacionRepository.save(Estacion.builder()
                .numero(nuevo.numero())
                .nombre(nuevo.nombre())
                .coordenadas(nuevo.coordenadas())
                .capacidad(nuevo.capacidad())
                .build()
        );
    }

    public Estacion edit(EditEstacionCmd editEstacionCmd, Long id){
        return estacionRepository.findById(id)
                .map(old -> {
                    old.setNumero(editEstacionCmd.numero());
                    old.setNombre(editEstacionCmd.nombre());
                    old.setCoordenadas(editEstacionCmd.coordenadas());
                    old.setCapacidad(editEstacionCmd.capacidad());
                    return estacionRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No hay estaciones con el id: "+ id +"."));
    }

    public void delete(Long id){
        estacionRepository.deleteById(id);
    }
}
