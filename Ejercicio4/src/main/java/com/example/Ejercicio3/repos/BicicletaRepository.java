package com.example.Ejercicio3.repos;

import com.example.Ejercicio3.model.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BicicletaRepository extends JpaRepository<Bicicleta, Long> {

    public int countBicicletaByMarca(String marca);
}
