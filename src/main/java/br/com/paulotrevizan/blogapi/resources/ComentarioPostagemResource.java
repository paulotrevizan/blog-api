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

import br.com.paulotrevizan.blogapi.domains.ComentarioPostagem;
import br.com.paulotrevizan.blogapi.dto.ComentarioPostagemRequest;
import br.com.paulotrevizan.blogapi.dto.response.ComentarioPostagemResponse;
import br.com.paulotrevizan.blogapi.services.ComentarioPostagemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/postagens")
@RequiredArgsConstructor
public class ComentarioPostagemResource {

  private final ComentarioPostagemService comentarioPostagemService;

  @PostMapping(value = "/{postagemId}/comentarios")
  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<Void> insert(@PathVariable final Integer postagemId,
      @Valid @RequestBody final ComentarioPostagemRequest request) {
    ComentarioPostagem comentario = comentarioPostagemService.insert(postagemId, request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comentario.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping(value = "/{postagemId}/comentarios/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<ComentarioPostagemResponse> getComentarioPostagemById(
      @PathVariable(name = "postagemId") final Integer postagemId, @PathVariable(name = "id") final Integer id) {
    return ResponseEntity.ok(comentarioPostagemService.getComentarioPostagemById(postagemId, id));
  }

  @DeleteMapping(value = "/{postagemId}/comentarios/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public ResponseEntity<Void> deleteById(@PathVariable(name = "postagemId") final Integer postagemId,
      @PathVariable(name = "id") final Integer id) {
    comentarioPostagemService.delete(postagemId, id);
    return ResponseEntity.noContent().build();
  }

}
