package br.com.paulotrevizan.blogapi.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.paulotrevizan.blogapi.domains.Usuario;
import br.com.paulotrevizan.blogapi.dto.UsuarioRequest;
import br.com.paulotrevizan.blogapi.dto.response.UsuarioResponse;
import br.com.paulotrevizan.blogapi.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {

  private final UsuarioService usuarioService;

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<Void> insert(@Valid @RequestBody final UsuarioRequest request) {
    Usuario user = usuarioService.insert(request);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping(value = "/{username}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<UsuarioResponse> getUsuarioByUsername(@PathVariable(name = "username") final String username) {
    return ResponseEntity.ok(usuarioService.getUsuarioByUsername(username));
  }

}
