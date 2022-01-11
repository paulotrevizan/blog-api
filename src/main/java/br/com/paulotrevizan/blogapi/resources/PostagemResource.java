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

import br.com.paulotrevizan.blogapi.domains.Postagem;
import br.com.paulotrevizan.blogapi.dto.PostagemRequest;
import br.com.paulotrevizan.blogapi.dto.response.PostagemResponse;
import br.com.paulotrevizan.blogapi.services.PostagemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/postagens")
@RequiredArgsConstructor
public class PostagemResource {

  private final PostagemService postagemService;

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<Void> insert(@Valid @RequestBody final PostagemRequest request) {
    Postagem postagem = postagemService.insert(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postagem.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<PostagemResponse> getPostagemById(@PathVariable(name = "id") final Integer id) {
    return ResponseEntity.ok(postagemService.getPostagemById(id));
  }

  @GetMapping
  public ResponseEntity<List<PostagemResponse>> getAllPostagens(
      @RequestParam(name = "usuarioId", required = false) final Integer usuarioId) {
    return ResponseEntity.ok(postagemService.getAllPostagens(usuarioId));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public ResponseEntity<Void> deleteById(@PathVariable(name = "id") final Integer id) {
    postagemService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
