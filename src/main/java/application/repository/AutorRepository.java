package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import application.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    
}
