package com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.services;

import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models.DatosDeLibros;

import java.util.List;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
    List<DatosDeLibros> obtenerLista(String json, Class<DatosDeLibros> datosLibrosClass);
}
