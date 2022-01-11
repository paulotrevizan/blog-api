package br.com.paulotrevizan.blogapi.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class ComentarioPostagemResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private UsuarioResponse usuario;
  private String conteudo;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate data;

}
