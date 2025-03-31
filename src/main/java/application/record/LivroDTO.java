package application.record;

import application.model.Livro;
import java.util.HashSet;

import java.util.stream.Collectors;

public record LivroDTO(
    long id,
    String titulo,
    GeneroDTO genero,
    HashSet<AutorDTO> autores
) {
    public LivroDTO(Livro livro) {
        this(
            livro.getId(),
            livro.getTitulo(),
            new GeneroDTO(livro.getGenero()),
            livro.getAutores().stream().map(AutorDTO::new).collect(Collectors.toCollection(HashSet::new))
        );
    }
}
