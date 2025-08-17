package com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.principal;

import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.repository.LibrosRepository;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.services.ConsumoDeApi;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.services.ConvierteDatos;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/";
    private final java.util.Scanner teclado = new java.util.Scanner(System.in);
    private final ConsumoDeApi consumoApi;
    private final ConvierteDatos conversor;
    private final LibrosRepository repositorio;
    private final LibroService libroService;


    @Autowired
    public Principal(LibrosRepository repositorio, LibroService libroService) {
        this.repositorio = repositorio;
        this.libroService = libroService;
        // Se instancian manualmente; alternativamente podrías inyectarlos
        this.consumoApi = new ConsumoDeApi();
        this.conversor = new ConvierteDatos();
    }

    public void muestraElMenu() {
        int opcion;
        do {
            System.out.println("""
        **************************************
        *       📚 MENÚ PRINCIPAL 📚         *
        **************************************
          1️⃣  🔍 Buscar Libro por Título
          2️⃣  📖 Listar Libros Registrados   
          3️⃣  ✍️ Listar Autores Registrados 
          4️⃣  🏛️ Listar Autores por Año    
          5️⃣  🌍 Listar Libros por Idioma
          0️⃣  ❌ Salir                     
        **************************************
        """);

            System.out.print("\n🟢 Selecciona una opción: ");
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar scanner

                switch (opcion) {
                    case 1 -> {
                        var libro = buscarLibroTitulo();
                        if (libro == null) {
                            System.out.println("Volviendo al menú de inicio...");
                        }
                    }
                    case 2 -> mostrarLibrosRegistrados();
                    case 3 -> buscarLibroAutores();
                    case 4 -> buscarLibrosAutoresVivos();
                    case 5 -> buscarLibrosPorIdioma();
                    case 0 -> System.out.println("🔴 Cerrando el sistema...");
                    default -> System.out.println("⚠️ Opción no válida.");
                }
            } else {
                System.out.println("❌ Entrada inválida, por favor ingresa un número.");
                teclado.nextLine(); // Limpiar entrada incorrecta
                opcion = -1;
            }
        } while (opcion != 0);
    }
}
