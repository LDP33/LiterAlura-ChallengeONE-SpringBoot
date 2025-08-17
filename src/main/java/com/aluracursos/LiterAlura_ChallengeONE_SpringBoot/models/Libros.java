package com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models;

import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @Column(name = "idiomas", columnDefinition = "TEXT")
    @Convert(converter = ListToStringConverter.class)
    private List<String> idiomas;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    @Convert(converter = ListToStringConverter.class)
    private List<String> descripcion;

    @Column(name = "autores", columnDefinition = "TEXT")
    @Convert(converter = ListToStringConverter.class)
    private List<String> autores;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

}
