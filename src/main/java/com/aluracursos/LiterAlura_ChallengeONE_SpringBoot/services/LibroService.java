package com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.services;

import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models.DatosDeLibros;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models.Libros;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.repository.LibrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class LibroService {
    private final LibrosRepository libroRepository;

    public LibroService(LibrosRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libros guardarLibro(DatosDeLibros datosLibros) {
        Libros libro = new Libros(datosLibros);
        return libroRepository.save(libro);
    }

    public Optional<Libros> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainsIgnoreCase(titulo);
    }

    public List<Libros> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Map<String, Long> obtenerEstadisticasPorIdioma() {
        return Map.of();
    }

    public List<Libros> obtenerLibrosPorIdioma(String idioma) {
        List<Libros> resultado = libroRepository.findByIdiomaIgnoreCase(idioma);
        System.out.println("🔍 Libros encontrados en idioma '" + idioma + "': " + resultado.size());
        return resultado;
    }
}
