package application.record;

import java.util.HashSet;

public record LivroInsertDTO(String titulo, long id_genero, HashSet<AutorDTO> autores) {
    
}
