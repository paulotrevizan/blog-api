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
public class PostagemRequest {

  @NotNull
  private Integer usuarioId;

  @NotEmpty
  private String titulo;

  @NotEmpty
  private String conteudo;

  @NotNull
  private byte[] imagemCapa;

}
