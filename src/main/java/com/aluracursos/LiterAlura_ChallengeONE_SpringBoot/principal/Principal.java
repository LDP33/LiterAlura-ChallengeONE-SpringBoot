package com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.principal;

import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models.Datos;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models.DatosDeLibros;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models.Libros;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.repository.LibrosRepository;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.services.ConsumoDeApi;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.services.ConvierteDatos;
import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                System.out.println("❌ Entrada no válida, por favor ingresa un número.");
                teclado.nextLine(); // Limpiar entrada incorrecta
                opcion = -1;
            }
        } while (opcion != 0);
    }
    private DatosDeLibros buscarLibroTitulo() {
        System.out.println("Escribe el título del libro que deseas buscar:");
        var tituloLibro = teclado.nextLine().trim();

        try {
            String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
            System.out.println("JSON recibido: " + json);

            if (json.equals("{}")) {
                System.out.println("❌ No se encontraron datos del libro.");
                return null;
            }

            Datos datos = conversor.obtenerDatos(json, Datos.class);

            if (datos.resultados().isEmpty()) {
                System.out.println("❌ No se encontraron libros con el título elegido.");
                return null;
            }

            DatosDeLibros libroEncontrado = datos.resultados().stream().findFirst().orElse(null);
            if (libroEncontrado == null) {
                System.out.println("❌ Error. Problemas al obtener datos del libro.");
                return null;
            }

            System.out.println("\nLibro encontrado:");
            System.out.println("Título: " + libroEncontrado.titulo());
            System.out.println("Autor/es: " + libroEncontrado.autor());
            System.out.println("Idiomas: " + libroEncontrado.idiomas());
            System.out.println("Descripción: " + libroEncontrado.descripcion());
            System.out.println("\n***************************************\n");

            Optional<Libros> libroRegistradoOpt = repositorio.findByTituloIgnoreCase(libroEncontrado.titulo().trim());
            if (libroRegistradoOpt.isPresent()) {
                System.out.println("✅ El libro ya está registrado en la base de datos.");
            } else {
                Libros libroDb = new Libros(libroEncontrado);
                Libros libroGuardado = repositorio.save(libroDb);
                System.out.println("✅ Libro registrado en la base de datos con ID: " + libroGuardado.getId());
            }

            return libroEncontrado;
        } catch (Exception e) {
            System.out.println("❌ Ocurrió un error al buscar el libro: " + e.getMessage());
            return null;
        }
    }
    private void mostrarLibrosRegistrados() {
        var libros = repositorio.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
        } else {
            System.out.println("Libros registrados:");
            for (var libro : libros) {
                System.out.println("ID: " + libro.getId() +
                        " | Título: " + libro.getTitulo() +
                        " | Autores: " + libro.getAutores() +
                        " | Descripción: " + libro.getDescripcion());

            }
        }
    }

    private void buscarLibroAutores() {
        System.out.println("Escribe el Apellido del autor que deseas buscar:");
        String autorBusqueda = teclado.nextLine().trim();

        List<Libros> librosPorAutor = repositorio.findByAutorLike(autorBusqueda);

        if (librosPorAutor.isEmpty()) {
            System.out.println("❌ No se encontraron libros del autor: " + autorBusqueda);
        } else {
            System.out.println("Libros encontrados para el autor '" + autorBusqueda + "':");
            for (Libros libro : librosPorAutor) {
                System.out.println("ID: " + libro.getId()
                        + " | Título: " + libro.getTitulo()
                        + " | Autores: " + libro.getAutores());
            }
        }
    }
    private void buscarLibrosAutoresVivos() {
        try {
            System.out.println("Ingresa el año inicial del rango (para Birth Year):");
            int anioInicio = Integer.parseInt(teclado.nextLine().trim());
            System.out.println("Ingresa el año final del rango (para Birth Year):");
            int anioFin = Integer.parseInt(teclado.nextLine().trim());

            List<Libros> libros = libroService.obtenerTodos();

            libros.forEach(libro -> System.out.println("Libro: " + libro.getTitulo() + " | Birth Year: " + libro.getBirthYear()));

            List<Libros> librosFiltrados = libros.stream()
                    .filter(libro -> libro.getBirthYear() != null && libro.getBirthYear() > 0) // Filtra valores inválidos
                    .peek(libro -> System.out.println("Filtrando: " + libro.getTitulo() + " | Birth Year: " + libro.getBirthYear())) // Depuración
                    .filter(libro -> libro.getBirthYear() >= anioInicio && libro.getBirthYear() <= anioFin) // Aplica el rango de años
                    .toList();

            if (librosFiltrados.isEmpty()) {
                System.out.println("No se encontraron libros de autores nacidos entre " + anioInicio + " y " + anioFin);
            } else {
                System.out.println("Libros encontrados para autores nacidos entre " + anioInicio + " y " + anioFin + ":");
                for (Libros libro : librosFiltrados) {
                    System.out.println(" Autor(es): " + String.join(", ", libro.getAutores()+ " | Birth Year: " + libro.getBirthYear()));
                }
            }

        } catch (Exception e) {
            System.out.println("Error al procesar el rango de años: " + e.getMessage());
        }
    }
    private void buscarLibrosPorIdioma() {
        System.out.println("""
        Selecciona el idioma del libro que deseas buscar:
        1 - Español (es)
        2 - Inglés (en)
        3 - Francés (fr)
        4 - Alemán (de)
        5 - Italiano (it)
        6 - Portugués (pt)
        0 - Volver al menú
        """);

        int opcionIdioma = teclado.nextInt();
        teclado.nextLine(); // Limpiar el buffer

        String idiomaBusqueda = switch (opcionIdioma) {
            case 1 -> "es";
            case 2 -> "en";
            case 3 -> "fr";
            case 4 -> "de";
            case 5 -> "it";
            case 6 -> "pt";
            case 0 -> {
                System.out.println("🔙 Volviendo al menú...");
                yield null;
            }
            default -> {
                System.out.println("⚠️ Opción inválida, volviendo al menú...");
                yield null;
            }
        };

        if (idiomaBusqueda != null) {
            List<Libros> librosPorIdioma = libroService.obtenerLibrosPorIdioma(idiomaBusqueda);

            if (librosPorIdioma.isEmpty()) {
                System.out.println("❌ No se encontraron libros en el idioma: " + idiomaBusqueda);
            } else {
                System.out.println("📚 Libros encontrados en " + idiomaBusqueda + ":");
                librosPorIdioma.forEach(libro -> System.out.println("🔹 " + libro.getTitulo()));
            }

            Map<String, Long> estadisticas = libroService.obtenerEstadisticasPorIdioma();
            System.out.println("\n📊 Estadísticas de los libros por idioma:");
            estadisticas.forEach((idioma, cantidad) ->
                    System.out.println("🌍 Idioma: " + idioma + " - Libros disponibles: " + cantidad));
        }
    }
}
