package com.example.Ejercicio3.controller;

import com.example.Ejercicio3.dto.EditBicicletaCmd;
import com.example.Ejercicio3.dto.GetBicicletaDto;
import com.example.Ejercicio3.model.Bicicleta;
import com.example.Ejercicio3.service.BicicletaService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bicicleta/")
@RequiredArgsConstructor
public class BicicletaController {

    private final BicicletaService bicicletaService;

    @GetMapping
    public List<GetBicicletaDto> getAll(){
        return bicicletaService.findAll()
                .stream()
                .map(GetBicicletaDto::of)
                .toList();
    }

    @GetMapping("/{id}")
    public GetBicicletaDto findById(@PathVariable Long id){
        return GetBicicletaDto.of(bicicletaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Bicicleta> save(@RequestBody EditBicicletaCmd nuevo){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bicicletaService.save(nuevo));
    }

    @PutMapping("/{id}")
    public Bicicleta edit(@RequestBody EditBicicletaCmd aEditar, @PathVariable("id") Long id){
        return bicicletaService.edit(aEditar, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        bicicletaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
