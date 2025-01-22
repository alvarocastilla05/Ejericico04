package com.example.Ejercicio3.service;

import com.example.Ejercicio3.dto.EditBicicletaCmd;
import com.example.Ejercicio3.model.Bicicleta;
import com.example.Ejercicio3.repos.BicicletaRepository;
import com.example.Ejercicio3.repos.EstacionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BicicletaService {

    private final BicicletaRepository bicicletaRepository;
    private final EstacionRepository estacionRepository;

    public List<Bicicleta> findAll(){
        List<Bicicleta> result = bicicletaRepository.findAll();

        if(result.isEmpty())
            throw new EntityNotFoundException("No hay bicicletas con esos criterios de búsqueda.");
        return result;
    }

    public Bicicleta findById(Long id){
        return bicicletaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No hay bicicletas con el id: "+ id +"."));
    }

    public Bicicleta save(EditBicicletaCmd nuevo){
        return bicicletaRepository.save(Bicicleta.builder()
                .marca(nuevo.marca())
                .modelo(nuevo.modelo())
                .estado(nuevo.estado())
                .estacion(estacionRepository.findById(nuevo.estacionId())
                        .orElseThrow(() -> new EntityNotFoundException("No hay ninguna estación con ese id.")))
                .build()
        );
    }

    public Bicicleta edit(EditBicicletaCmd editBicicletaCmd, Long id){
        return bicicletaRepository.findById(id)
                .map(old -> {
                    old.setMarca(editBicicletaCmd.marca());
                    old.setModelo(editBicicletaCmd.modelo());
                    old.setEstado(editBicicletaCmd.estado());
                    old.setEstacion(estacionRepository.findById(editBicicletaCmd.estacionId())
                            .orElseThrow(() -> new EntityNotFoundException("No hay ninguna estación con ese id.")));
                    return bicicletaRepository.save(old);
                        })
                .orElseThrow(() -> new EntityNotFoundException("No hay ninguna bicicleta con id: "+ id +"."));

    }

    public void delete(Long id){
        bicicletaRepository.deleteById(id);
    }

}
