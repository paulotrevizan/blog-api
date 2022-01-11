package br.com.paulotrevizan.blogapi.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class AlbumFotoRequest {

  @NotNull
  private Integer usuarioId;

  @NotEmpty
  private String nome;

}
