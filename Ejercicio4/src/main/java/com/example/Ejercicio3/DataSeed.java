package com.example.Ejercicio3;

import com.example.Ejercicio3.model.Bicicleta;
import com.example.Ejercicio3.model.Estacion;
import com.example.Ejercicio3.model.Uso;
import com.example.Ejercicio3.model.Usuario;
import com.example.Ejercicio3.repos.BicicletaRepository;
import com.example.Ejercicio3.repos.EstacionRepository;
import com.example.Ejercicio3.repos.UsoRepository;
import com.example.Ejercicio3.repos.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final EstacionRepository estacionRepository;
    private final BicicletaRepository bicicletaRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsoRepository usoRepository;

    @PostConstruct
    public void run(){

        // Crear estaciones.

        Estacion estacion1 = Estacion.builder()
                .numero(1L)
                .nombre("Estación Central")
                .coordenadas("40.4168, -3.7038")
                .capacidad(10)
                .build();

        Estacion estacion2 = Estacion.builder()
                .numero(2L)
                .nombre("Estación Norte")
                .coordenadas("41.3825, 2.1769")
                .capacidad(15)
                .build();

        estacionRepository.save(estacion1);
        estacionRepository.save(estacion2);

        // Crear bicicletas y asociarlas a estaciones.

        Bicicleta bicicleta1 = Bicicleta.builder()
                .marca("MarcaX")
                .modelo("ModeloA")
                .estado("Disponible")
                .build();

        Bicicleta bicicleta2 = Bicicleta.builder()
                .marca("MarcaY")
                .modelo("ModeloB")
                .estado("Disponible")
                .build();

        Bicicleta bicicleta3 = Bicicleta.builder()
                .marca("MarcaY")
                .modelo("ModeloB")
                .estado("Disponible")
                .build();

        Bicicleta bicicleta4 = Bicicleta.builder()
                .marca("MarcaY")
                .modelo("ModeloB")
                .estado("Disponible")
                .build();

        Bicicleta bicicleta5 = Bicicleta.builder()
                .marca("MarcaC")
                .modelo("ModeloB")
                .estado("Disponible")
                .build();

        Bicicleta bicicleta6 = Bicicleta.builder()
                .marca("MarcaY")
                .modelo("ModeloB")
                .estado("Disponible")
                .build();

        estacion1.addBicicleta(bicicleta1);
        estacion2.addBicicleta(bicicleta2);
        estacion1.addBicicleta(bicicleta3);
        estacion1.addBicicleta(bicicleta4);
        estacion1.addBicicleta(bicicleta5);
        estacion2.addBicicleta(bicicleta6);

        bicicletaRepository.save(bicicleta1);
        bicicletaRepository.save(bicicleta2);
        bicicletaRepository.save(bicicleta3);
        bicicletaRepository.save(bicicleta4);
        bicicletaRepository.save(bicicleta5);
        bicicletaRepository.save(bicicleta6);





        // Crear usuarios.

        Usuario usuario1 = Usuario.builder()
                .nombre("Juan Pérez")
                .numTarjeta(1234567890123456L)
                .pin(1234)
                .saldo(100.0)
                .build();

        Usuario usuario2 = Usuario.builder()
                .nombre("María López")
                .numTarjeta(9876543210987654L)
                .pin(5678)
                .saldo(50.0)
                .build();

        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);

        // Crear usos y asociarlos a usuarios, bicicletas y estaciones.

        Uso uso1 = Uso.builder()
                .fechaInicio(LocalDate.of(2025, 1, 20))
                .fechaFin(LocalDate.of(2025, 1, 20))
                .coste(5.0)
                .build();

        Uso uso2 = Uso.builder()
                .fechaInicio(LocalDate.of(2025, 3, 21))
                .fechaFin(null)   // Está en curso. :P
                .coste(0.0)
                .build();

        Uso uso3 = Uso.builder()
                .fechaInicio(LocalDate.of(2025, 2, 18))
                .fechaFin(LocalDate.of(2025, 2, 19))
                .coste(3.20)
                .build();

        // Asociaciones bidireccionales para los usos.

        usuario1.addUso(uso1);
        bicicleta1.addUso(uso1);
        estacion1.addUso(uso1);

        usuario2.addUso(uso2);
        bicicleta2.addUso(uso2);
        estacion2.addUso(uso2);

        usuario1.addUso(uso3);
        bicicleta1.addUso(uso3);
        estacion1.addUso(uso3);

        usoRepository.save(uso1);
        usoRepository.save(uso2);
        usoRepository.save(uso3);

        System.out.println(usuario1);
        System.out.println(usuario2);

        System.out.println(uso1);
        System.out.println(uso2);

        System.out.println(bicicleta1);
        System.out.println(bicicleta2);

        System.out.println(estacion1);
        System.out.println(estacion2);

        usoRepository.delete(uso1);

        System.out.println(bicicletaRepository.countBicicletaByMarca("MarcaY"));


    }

}