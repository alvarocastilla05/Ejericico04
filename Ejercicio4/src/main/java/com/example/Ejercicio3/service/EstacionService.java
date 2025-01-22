package com.example.Ejercicio3.service;

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
            throw new EntityNotFoundException("No se encontraron estaciones con esos criterios de busqueda");
        return result;
    }

    public Estacion findById(Long id){
        return estacionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No hay estacion con id: " +id));
    }

    public Estacion save(Estacion estacion){
        return estacionRepository.save(estacion);
    }

    public Estacion edit(Estacion estacion, Long id){
        return estacionRepository.findById(id)
                .map(old -> {
                    old.setNumero((estacion.getNumero()));
                    old.setNombre(estacion.getNombre());
                    old.setCoordenadas(estacion.getCoordenadas());
                    old.setCapacidad(estacion.getCapacidad());
                    return estacionRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No hay estaciones con el id:" +id));
    }

    public void delete(Long id){
        estacionRepository.deleteById(id);
    }
}
