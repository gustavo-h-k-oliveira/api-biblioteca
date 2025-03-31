package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.model.Livro;
import application.model.Autor;
import application.record.AutorDTO;
import application.record.LivroDTO;
import application.record.LivroInsertDTO;
import application.repository.GeneroRepository;
import application.repository.LivroRepository;
import application.repository.AutorRepository;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepo;
    @Autowired
    private AutorRepository autorRepo;
    @Autowired
    private GeneroRepository generoRepo;
    
    public Iterable<LivroDTO> getAll() {
        return livroRepo.findAll().stream().map(LivroDTO::new).toList();
    }

    public LivroDTO getOne(long id) {
        Optional<Livro> resultado = livroRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro Não Encontrado"
            );
        }
        
        return new LivroDTO(resultado.get());
    }

    public LivroDTO insert(LivroInsertDTO livro) {
        if (!generoRepo.existsById(livro.id_genero())) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Gênero Não Encontrado"
            );
        }

        for (AutorDTO a : livro.autores()) {
            if (!autorRepo.existsById(a.id())) {
                throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Autor Não Encontrado"
                );
            }
        }

        Livro newLivro = new Livro(livro);
        Livro savedLivro = livroRepo.save(newLivro);
        LivroDTO response = new LivroDTO(savedLivro);
        return response;
    }

    public LivroDTO update(long id, LivroDTO livro) {
        Optional<Livro> resultado = livroRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro Não Encontrado"
            );
        }

        resultado.get().setTitulo(livro.titulo());
        // resultado.get().setGeneros(livro.generos());
        resultado.get().setAutores(livro.autores()
            .stream().map(Autor::new)
            .collect(Collectors.toCollection(HashSet::new))
        );

        return new LivroDTO(livroRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!livroRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro Não Encontrado"
            );
        }

        livroRepo.deleteById(id);
    }
}
