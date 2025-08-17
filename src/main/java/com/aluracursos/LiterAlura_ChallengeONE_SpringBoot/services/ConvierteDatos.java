package com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.services;

import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models.DatosDeLibros;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DatosDeLibros> obtenerLista(String json, Class<DatosDeLibros> datosLibrosClass) {
        try {

            return objectMapper.readValue(
                    json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, datosLibrosClass)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
