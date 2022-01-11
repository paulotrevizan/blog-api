package br.com.paulotrevizan.blogapi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.paulotrevizan.blogapi.domains.AlbumFoto;
import br.com.paulotrevizan.blogapi.dto.AlbumFotoRequest;
import br.com.paulotrevizan.blogapi.dto.response.AlbumFotoResponse;
import br.com.paulotrevizan.blogapi.services.AlbumFotoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/albuns")
@RequiredArgsConstructor
public class AlbumFotoResource {

  private final AlbumFotoService albumFotoService;

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<Void> insert(@Valid @RequestBody final AlbumFotoRequest request) {
    AlbumFoto albumFoto = albumFotoService.insert(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(albumFoto.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<AlbumFotoResponse> getAlbumFotoById(@PathVariable(name = "id") final Integer id) {
    return ResponseEntity.ok(albumFotoService.getAlbumFotoById(id));
  }

  @GetMapping
  public ResponseEntity<List<AlbumFotoResponse>> getAllAlbuns(
      @RequestParam(name = "usuarioId", required = false) final Integer usuarioId) {
    return ResponseEntity.ok(albumFotoService.getAllAlbuns(usuarioId));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public ResponseEntity<Void> deleteById(@PathVariable(name = "id") final Integer id) {
    albumFotoService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
