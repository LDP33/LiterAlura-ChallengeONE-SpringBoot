package com.aluracursos.LiterAlura_ChallengeONE_SpringBoot;

import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models.Libros;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @PostMapping("/guardar")
    public ResponseEntity<Libros> guardarLibro(@RequestBody DatosLibros datosLibros) {
        Libros libroGuardado = libroService.guardarLibro(datosLibros);
        return ResponseEntity.status(HttpStatus.CREATED).body(libroGuardado);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Libros>> buscarLibrosPorTitulo(@RequestParam String titulo) {
        List<Libros> libros = libroService.obtenerTodos();
        return ResponseEntity.ok(libros);
    }
}
