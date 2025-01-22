package com.example.Ejercicio3.controller;

import com.example.Ejercicio3.dto.EditBicicletaCmd;
import com.example.Ejercicio3.dto.EditEstacionCmd;
import com.example.Ejercicio3.dto.GetEstacionDto;
import com.example.Ejercicio3.model.Bicicleta;
import com.example.Ejercicio3.model.Estacion;
import com.example.Ejercicio3.service.EstacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estacion/")
@Tag(name = "Estación",
        description = "Controlador de Estación, para poder realizar sus operaciones de gestión."
)
public class EstacionController {

    private final EstacionService estacionService;

    @Operation(summary = "Obtiene una lista de todas las estaciones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado estaciones.",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Estacion.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                              {
                                                  "id": 1, "nombre": "Estación Central",
                                                  "coordenadas": "40.4168, -3.7038", "capacidad": 10
                                              },
                                              {
                                                  "id": 2, "nombre": "Estación Norte",
                                                  "coordenadas": "41.3825, 2.1769", "capacidad": 15
                                              }
                                            ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna estación.",
                    content = @Content),
    })
    @GetMapping
    public List<GetEstacionDto> getAll(){
        return estacionService.findAll()
                .stream()
                .map(GetEstacionDto::of)
                .toList();
    }

    @Operation(summary = "Obtiene la estación buscada por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la estación.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Estacion.class),
                            examples = {@ExampleObject(
                                    value = """
                                        {
                                            "id": 3, "nombre": "Estación Western",
                                            "coordenadas": "10.90, -3.758", "capacidad": 20
                                        }                                       
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna estación con ese ID.",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetEstacionDto findById(@PathVariable Long id){
        return GetEstacionDto.of(estacionService.findById(id));
    }

    @Operation(summary = "Crea una nueva estación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la estación correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditEstacionCmd.class),
                            examples = {@ExampleObject(
                                    value = """
                                        {
                                            "id": 3, "nombre": "Estación Western",
                                            "coordenadas": "10.90, -3.758", "capacidad": 20
                                        }                                     
                                        """
                            )}
                    )}),
    })
    @PostMapping
    public GetEstacionDto save(@RequestBody EditEstacionCmd nuevo){
        return GetEstacionDto.of(estacionService.save(nuevo));
    }

    @Operation(summary = "Edita una estación ya creada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la estación correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditEstacionCmd.class),
                            examples = {@ExampleObject(
                                    value = """
                                        {
                                            "id": 1, "nombre": "Estación Betocha",
                                            "coordenadas": "4, -8", "capacidad": 80
                                        }                                   
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna estación con ese ID.",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public GetEstacionDto edit(@RequestBody EditEstacionCmd aEditar, @PathVariable("id") Long id){
        return GetEstacionDto.of(estacionService.edit(aEditar, id));
    }

    @Operation(summary = "Borra una estación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la estación correctamente.",
                    content = { @Content(mediaType = "application/json")}
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        estacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
