package com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(@JsonAlias("results") List<DatosDeLibros> resultados) {
}
