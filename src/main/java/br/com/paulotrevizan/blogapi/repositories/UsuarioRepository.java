package br.com.paulotrevizan.blogapi.repositories;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulotrevizan.blogapi.domains.Usuario;

@Lazy
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  Optional<Usuario> findByUsername(String username);

  boolean existsByUsername(String username);

}
