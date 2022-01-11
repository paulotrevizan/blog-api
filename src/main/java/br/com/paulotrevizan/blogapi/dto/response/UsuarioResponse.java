package br.com.paulotrevizan.blogapi.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class UsuarioResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String username;
  private String nome;

}
