package br.com.paulotrevizan.blogapi.repositories;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.paulotrevizan.blogapi.domains.Foto;

@Lazy
public interface FotoRepository extends JpaRepository<Foto, Integer> {

  Optional<Foto> findByAlbumFotoIdAndId(Integer albumFotoId, Integer id);

}
