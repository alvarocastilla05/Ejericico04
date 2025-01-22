package com.example.Ejercicio3.controller;

import com.example.Ejercicio3.dto.EditBicicletaCmd;
import com.example.Ejercicio3.dto.GetBicicletaDto;
import com.example.Ejercicio3.model.Bicicleta;
import com.example.Ejercicio3.service.BicicletaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bicicleta/")
@RequiredArgsConstructor
@Tag(name = "Bicicleta",
    description = "Controlador de Bicicleta, para poder realizar sus operaciones de gestión."
)
public class BicicletaController {

    private final BicicletaService bicicletaService;

    @Operation(summary = "Obtiene una lista de todas las bicicletas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado bicicletas.",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Bicicleta.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                {
                                                    "id": 1, "marca": "MarcaX", "modelo": "ModeloA", "estado": "Disponible",
                                                    "estacion": {
                                                        "id": 1,
                                                        "nombre": "Estación Central",
                                                        "coordenadas": "40.4168, -3.7038",
                                                        "capacidad": 10
                                                    }
                                                },
                                                {
                                                     "id": 2, "marca": "MarcaY", "modelo": "ModeloB", "estado": "Disponible",
                                                     "estacion": {
                                                        "id": 2,
                                                        "nombre": "Estación Norte",
                                                        "coordenadas": "41.3825, 2.1769",
                                                        "capacidad": 15
                                                     }
                                                }
                                            ]                                          
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna bicicleta.",
                    content = @Content),
    })
    @GetMapping
    public List<GetBicicletaDto> getAll(){
        return bicicletaService.findAll()
                .stream()
                .map(GetBicicletaDto::of)
                .toList();
    }

    @Operation(summary = "Obtiene la bicicleta buscada por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la bicicleta.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Bicicleta.class),
                            examples = {@ExampleObject(
                                value = """
                                        {
                                            "id": 1, "marca": "MarcaX", "modelo": "ModeloA", "estado": "Disponible",
                                            "estacion": {
                                                "id": 1,
                                                "nombre": "Estación Central",
                                                "coordenadas": "40.4168, -3.7038",
                                                "capacidad": 10
                                            }
                                        }                                        
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna bicicleta con ese ID.",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public GetBicicletaDto findById(@PathVariable Long id){
        return GetBicicletaDto.of(bicicletaService.findById(id));
    }

    @Operation(summary = "Crea una nueva bicicleta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la bicicleta correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditBicicletaCmd.class),
                            examples = {@ExampleObject(
                                    value = """
                                        {
                                            "marca": "MarcaR",
                                            "modelo": "ModeloC",
                                            "estado": "Rota",
                                            "estacionId": 2
                                        }                                      
                                        """
                            )}
                    )}),
    })
    @PostMapping
    public ResponseEntity<Bicicleta> save(@RequestBody EditBicicletaCmd nuevo){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bicicletaService.save(nuevo));
    }

    @Operation(summary = "Edita una bicicleta ya creada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado la bicicleta correctamente.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditBicicletaCmd.class),
                            examples = {@ExampleObject(
                                    value = """
                                        {
                                            "marca": "MarcaSHIRORO",
                                            "modelo": "ModeloRARARA",
                                            "estado": "Arreglada",
                                            "estacionId": 2
                                        }                                    
                                        """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna bicicleta con ese ID.",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public Bicicleta edit(@RequestBody EditBicicletaCmd aEditar, @PathVariable("id") Long id){
        return bicicletaService.edit(aEditar, id);
    }

    @Operation(summary = "Borra una bicicleta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado la bicicleta correctamente.",
                    content = { @Content(mediaType = "application/json")}
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        bicicletaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
