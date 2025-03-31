package application.model;

import application.record.LivroDTO;
import application.record.LivroInsertDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;

    @ManyToMany
    @JoinTable(name = "livros_possuem_autores",
        joinColumns = @JoinColumn(name = "id_livro"),
        inverseJoinColumns = @JoinColumn(name = "id_autor"))

    private Set<Autor> autores;

    public Livro(LivroDTO record) {
        this.id = record.id();
        this.titulo = record.titulo();
        this.genero = new Genero(record.genero());
        this.autores = record.autores().stream().map(Autor::new).collect(Collectors.toCollection(HashSet::new));
    }

    public Livro(LivroInsertDTO record) {
        this.titulo = record.titulo();
        Genero gen = new Genero();
        gen.setId(record.id_genero());
        this.genero = gen;
        this.autores = record.autores().stream().map(Autor::new).collect(Collectors.toCollection(HashSet::new));
    }
}
