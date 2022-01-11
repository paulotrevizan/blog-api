package br.com.paulotrevizan.blogapi.services.mapper;

import org.springframework.stereotype.Service;

import br.com.paulotrevizan.blogapi.domains.Foto;
import br.com.paulotrevizan.blogapi.dto.response.FotoResponse;

@Service
public class FotoResponseMapper {

  public FotoResponse domainToResponse(Foto domain) {
    return FotoResponse.builder()
            .id(domain.getId())
            .imagem(domain.getImagem())
            .build();
  }

}
