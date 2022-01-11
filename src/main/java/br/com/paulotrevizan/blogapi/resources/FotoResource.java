package br.com.paulotrevizan.blogapi.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.paulotrevizan.blogapi.domains.Foto;
import br.com.paulotrevizan.blogapi.dto.FotoRequest;
import br.com.paulotrevizan.blogapi.dto.response.FotoResponse;
import br.com.paulotrevizan.blogapi.services.FotoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/albuns")
@RequiredArgsConstructor
public class FotoResource {

  private final FotoService fotoService;

  @PostMapping(value = "/{albumFotoId}/fotos")
  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<Void> insert(@PathVariable final Integer albumFotoId,
      @Valid @RequestBody final FotoRequest request) {
    Foto foto = fotoService.insert(albumFotoId, request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(foto.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping(value = "/{albumFotoId}/fotos/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<FotoResponse> getFotoById(@PathVariable(name = "albumFotoId") final Integer albumFotoId,
      @PathVariable(name = "id") final Integer id) {
    return ResponseEntity.ok(fotoService.getFotoById(albumFotoId, id));
  }

  @DeleteMapping(value = "/{albumFotoId}/fotos/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public ResponseEntity<Void> deleteById(@PathVariable(name = "albumFotoId") final Integer albumFotoId,
      @PathVariable(name = "id") final Integer id) {
    fotoService.delete(albumFotoId, id);
    return ResponseEntity.noContent().build();
  }

}
