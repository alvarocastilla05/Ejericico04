package com.example.Ejercicio3.controller;

import com.example.Ejercicio3.dto.GetEstacionDto;
import com.example.Ejercicio3.model.Estacion;
import com.example.Ejercicio3.service.EstacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estacion/")
public class EstacionController {

    private final EstacionService estacionService;


    
    @GetMapping
    public List<GetEstacionDto> getAll(){
        return estacionService.findAll()
                .stream()
                .map(GetEstacionDto::of)
                .toList();
    }

    @GetMapping("/{id}")
    public GetEstacionDto findById(@PathVariable Long id){
        return GetEstacionDto.of(estacionService.findById(id));
    }

    @PostMapping
    public GetEstacionDto save(@RequestBody Estacion estacion){
        return GetEstacionDto.of(estacionService.save(estacion));
    }

    @PutMapping("/{id}")
    public GetEstacionDto edit(@RequestBody Estacion aEditar, @PathVariable("id") Long id){
        return GetEstacionDto.of(estacionService.edit(aEditar, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        estacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
