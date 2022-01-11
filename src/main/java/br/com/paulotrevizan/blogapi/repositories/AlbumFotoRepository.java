package br.com.paulotrevizan.blogapi.repositories;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulotrevizan.blogapi.domains.AlbumFoto;

@Lazy
public interface AlbumFotoRepository extends JpaRepository<AlbumFoto, Integer> {
  List<AlbumFoto> findByUsuarioId(Integer usuarioId);
}
