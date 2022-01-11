package br.com.paulotrevizan.blogapi.domains;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Entity
@Table(name = "postagem")
public class Postagem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuarioid", nullable = false)
  private Usuario usuario;

  @Column(name = "titulo", length = 65, nullable = false)
  private String titulo;

  @Column(name = "conteudo", nullable = false, columnDefinition = "text")
  private String conteudo;

  @Column(name = "imagemcapa", nullable = false, columnDefinition = "bytea")
  private byte[] imagemCapa;

  @Column(name = "data", nullable = false)
  private LocalDate data;

  @OneToMany(mappedBy = "postagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ComentarioPostagem> comentarios;

}
