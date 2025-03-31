package application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import application.record.AutorDTO;
import application.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping
    public Iterable<AutorDTO> list() {
        return autorService.getAll();
    }

    @GetMapping("/{id}")
    public AutorDTO findOne(@PathVariable long id) {
        return autorService.getOne(id);
    }

    @PostMapping
    public AutorDTO insert(@RequestBody AutorDTO dados) {
        return autorService.insert(dados);
    }

    @PutMapping("/{id}")
    public AutorDTO update(@PathVariable long id, @RequestBody AutorDTO dados) {
        return autorService.update(id, dados);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        autorService.delete(id);
    }
}
