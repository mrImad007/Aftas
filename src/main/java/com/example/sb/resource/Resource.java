package com.example.sb.resource;

import com.example.sb.service.Service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Component
public abstract class Resource<Dto,DtoRequest,Identifier> {
    protected Service<Dto,DtoRequest,Identifier> service;


    @PostMapping
    public ResponseEntity<DtoRequest> save(@Valid @RequestBody final DtoRequest request) {
        var savedDto = service.save(request);
        return (ResponseEntity<DtoRequest>) new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Dto> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Dto> find(@PathVariable("id") final Identifier id) {
        var foundedDto = service.find(id);
        if (foundedDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(foundedDto, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Dto> update(@PathVariable("id") final Identifier id, @Valid @RequestBody final Dto dto) {
        if(!service.isExist(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var updatedDto = service.update(id, dto);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Identifier id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Dto> partialUpdate(@PathVariable("id") final Identifier id, @Valid @RequestBody final Dto dto) {
        if(!service.isExist(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var updatedDto = service.partialUpdate(id, dto);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }
}
