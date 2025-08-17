package com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models;

import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @Column(name = "idiomas", columnDefinition = "TEXT")
    @Convert(converter = ConverterListToString.class)
    private List<String> idiomas;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    @Convert(converter = ConverterListToString.class)
    private List<String> descripcion;

    @Column(name = "autores", columnDefinition = "TEXT")
    @Convert(converter = ConverterListToString.class)
    private List<String> autores;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

    public Libros(DatosDeLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idiomas = datosLibros.idiomas();
        this.descripcion = datosLibros.descripcion();

        // Se guarda solo el nombre de los autores
        this.autores = datosLibros.autor()
                .stream()
                .map(DatosDeAutor::nombre)
                .collect(Collectors.toList());

        // Si existe un autor, se extrae su información de años
        if (!datosLibros.autor().isEmpty()) {
            DatosDeAutor primerAutor = datosLibros.autor().get(0);

            try {
                this.birthYear = Integer.valueOf(String.valueOf((primerAutor.fechaDeNacimiento() != null && !primerAutor.fechaDeNacimiento().isEmpty())
                        ? Integer.valueOf(primerAutor.fechaDeNacimiento())
                        : null));
            } catch (NumberFormatException e) {
                System.out.println("No se pudo convertir el birth_year: " + primerAutor.fechaDeNacimiento());
                this.birthYear = null;
            }
            try {
                this.deathYear = Integer.valueOf(String.valueOf((primerAutor.fechaDeFallecimiento() != null && !primerAutor.fechaDeFallecimiento().isEmpty())
                        ? Integer.valueOf(primerAutor.fechaDeFallecimiento())
                        : null));
            } catch (NumberFormatException e) {
                System.out.println("No se pudo convertir el death_year: " + primerAutor.fechaDeFallecimiento());
                this.deathYear = null;
            }
        }
        System.out.println("Autores a guardar: " + this.autores);
        System.out.println("Birth Year: " + this.birthYear + ", Death Year: " + this.deathYear);
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public List<String> getDescripcion() {
        return descripcion;
    }

    public List<String> getAutores() {
        return autores;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public void setDescripcion(List<String> descripcion) {
        this.descripcion = descripcion;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }
}
