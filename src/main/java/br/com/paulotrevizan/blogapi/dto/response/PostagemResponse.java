package br.com.paulotrevizan.blogapi.dto.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
public class PostagemResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private UsuarioResponse usuario;
  private String titulo;
  private String conteudo;
  private byte[] imagemCapa;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate data;

  private List<ComentarioPostagemResponse> comentarios;

}
