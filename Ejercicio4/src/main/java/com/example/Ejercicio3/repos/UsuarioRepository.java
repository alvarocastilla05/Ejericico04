package com.example.Ejercicio3.repos;

import com.example.Ejercicio3.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
