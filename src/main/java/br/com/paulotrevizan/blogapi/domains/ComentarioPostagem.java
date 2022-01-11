package br.com.paulotrevizan.blogapi.domains;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "comentariopostagem")
public class ComentarioPostagem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "postagemid", nullable = false)
  private Postagem postagem;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuarioid", nullable = false)
  private Usuario usuario;

  @Column(name = "conteudo", nullable = false, columnDefinition = "text")
  private String conteudo;

  @Column(name = "data", nullable = false)
  private LocalDate data;

}
