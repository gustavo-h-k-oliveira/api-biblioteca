package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.record.GeneroDTO;
import application.repository.GeneroRepository;
import application.model.Genero;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepo;

    public Iterable<GeneroDTO> getAll() {
        return this.generoRepo.findAll().stream().map(GeneroDTO::new).toList();
    }

    public GeneroDTO getOne(long id) {
        Optional<Genero> resultado = generoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Genero Não Encontrado"
            );
        }

        return new GeneroDTO(resultado.get());
    }

    public GeneroDTO insert(GeneroDTO genero) {
        return new GeneroDTO(generoRepo.save(new Genero(genero)));
    }

    public GeneroDTO update(long id, GeneroDTO dados) {
        Optional<Genero> resultado = generoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Genero Não Encontrado"
            );
        }

        resultado.get().setDescricao(dados.descricao());
        return new GeneroDTO(generoRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!generoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Genero Não Encontrado"
            );
        }

        generoRepo.deleteById(id);
    }
}
