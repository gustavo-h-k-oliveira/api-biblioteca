package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import application.record.AutorDTO;
import application.model.Autor;
import application.repository.AutorRepository;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepo;

    public Iterable<AutorDTO> getAll() {
        return this.autorRepo.findAll().stream().map(AutorDTO::new).toList();
    }

    public AutorDTO getOne(long id) {
        Optional<Autor> resultado = autorRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Autor Não Encontrado"
            );
        }

        return new AutorDTO(resultado.get());
    }

    public AutorDTO insert(AutorDTO dados) {
        return new AutorDTO(autorRepo.save(new Autor(dados)));
    }

    public AutorDTO update(long id, AutorDTO dados) {
        Optional<Autor> resultado = autorRepo.findById(id);

        if (resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Autor Não Encontrado"
            );
        }

        resultado.get().setNome(dados.nome());
        return new AutorDTO(autorRepo.save(resultado.get()));
    }
    
    public void delete(long id) {
        if (!autorRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Autor Não Encontrado"
            );
        }

        autorRepo.deleteById(id);
    }
}
