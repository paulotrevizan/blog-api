package br.com.paulotrevizan.blogapi.repositories;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulotrevizan.blogapi.domains.ComentarioPostagem;

@Lazy
public interface ComentarioPostagemRepository extends JpaRepository<ComentarioPostagem, Integer> {

  Optional<ComentarioPostagem> findByPostagemIdAndId(Integer postagemId, Integer id);

}
