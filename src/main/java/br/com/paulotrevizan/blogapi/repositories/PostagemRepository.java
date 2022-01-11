package br.com.paulotrevizan.blogapi.repositories;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulotrevizan.blogapi.domains.Postagem;

@Lazy
public interface PostagemRepository extends JpaRepository<Postagem, Integer> {
  List<Postagem> findByUsuarioId(Integer usuarioId);
}
