package application.record;

import application.model.Genero;

public record GeneroDTO(long id, String descricao) {
    
    public GeneroDTO(Genero entidade) {
        this(
            entidade.getId(),
            entidade.getDescricao()
        );
    }
}
