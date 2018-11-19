package org.om.karate.sample.resource;

import org.om.karate.sample.persistence.Emp;
import org.om.karate.sample.persistence.EmpRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emp")
public class EmpController {

    private EmpRepository repository;

    public EmpController(EmpRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Collection<Emp> getAll() {
        return repository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Emp getById(@PathVariable Long id) {
        return repository.findOne(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Emp create(@RequestBody Emp emp) {
        return repository.save(emp);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.delete(id);
    }
}
