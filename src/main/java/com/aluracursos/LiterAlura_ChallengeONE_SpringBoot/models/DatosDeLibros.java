package com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosDeLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("summaries") List<String> descripcion,
        @JsonAlias("authors") List<DatosDeAutor> autor
) {
}
