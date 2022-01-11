package br.com.paulotrevizan.blogapi.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.paulotrevizan.blogapi.domains.AlbumFoto;
import br.com.paulotrevizan.blogapi.domains.Foto;
import br.com.paulotrevizan.blogapi.dto.FotoRequest;
import br.com.paulotrevizan.blogapi.dto.response.AlbumFotoResponse;
import br.com.paulotrevizan.blogapi.dto.response.FotoResponse;
import br.com.paulotrevizan.blogapi.exceptions.NotFoundException;
import br.com.paulotrevizan.blogapi.repositories.FotoRepository;
import br.com.paulotrevizan.blogapi.services.mapper.FotoResponseMapper;

import lombok.RequiredArgsConstructor;

@Lazy
@Service
@RequiredArgsConstructor
public class FotoService {

  private static final String NOT_FOUND = "Foto não encontrada";

  private final FotoRepository fotoRepository;
  private final AlbumFotoService albumFotoService;
  private final FotoResponseMapper fotoResponseMapper;

  public Foto insert(final Integer albumFotoId, final FotoRequest request) {
    AlbumFotoResponse albumFoto = albumFotoService.getAlbumFotoById(albumFotoId);

    Foto foto = Foto.builder()
            .albumFoto(AlbumFoto.builder().id(albumFoto.getId()).build())
            .imagem(request.getImagem())
            .build();

    return fotoRepository.save(foto);
  }

  public FotoResponse getFotoById(final Integer albumFotoId, final Integer id) {
    return fotoRepository.findByAlbumFotoIdAndId(albumFotoId, id)
            .map(fotoResponseMapper::domainToResponse)
            .orElseThrow(() -> new NotFoundException(NOT_FOUND));
  }

  public void delete(final Integer albumFotoId, final Integer id) {
    FotoResponse foto = getFotoById(albumFotoId, id);
    fotoRepository.deleteById(foto.getId());
  }

}
