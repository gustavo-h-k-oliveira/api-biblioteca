package application.record;

import application.model.Autor;

public record AutorDTO(long id, String nome) {
    public AutorDTO(Autor autor) {
        this(autor.getId(), autor.getNome());
    }
}
