package com.example.Library.controller;

import com.example.Library.model.dto.ThesisDTO;
import com.example.Library.service.interfaces.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/theses")
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    @PostMapping
    public ResponseEntity<ThesisDTO> add(@RequestBody ThesisDTO thesisDTO) {
        ThesisDTO created = thesisService.add(thesisDTO);
        URI location = URI.create("/api/theses/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThesisDTO> getById(@PathVariable Long id) {
        try {
            ThesisDTO thesis = thesisService.getById(id);
            return ResponseEntity.ok(thesis);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ThesisDTO>> getAll() {
        List<ThesisDTO> theses = thesisService.getAll();
        return ResponseEntity.ok(theses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThesisDTO> update(@PathVariable Long id, @RequestBody ThesisDTO thesisDTO) {
        try {
            ThesisDTO updated = thesisService.update(id, thesisDTO);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            thesisService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        thesisService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
