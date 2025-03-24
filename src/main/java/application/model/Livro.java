package application.model;

import application.record.LivroDTO;
import application.record.LivroInsertDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String autores;

    public Livro(LivroDTO record) {
        this.id = record.id();
        this.titulo = record.titulo();
        this.genero = new Genero(record.genero());
        this.autores = record.autores();
    }

    public Livro(LivroInsertDTO record) {
        this.titulo = record.titulo();
        Genero gen = new Genero();
        gen.setId(record.id_genero());
        this.genero = gen;
        this.autores = record.autores();
    }
}
