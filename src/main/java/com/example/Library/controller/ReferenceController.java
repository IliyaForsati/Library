package com.example.Library.controller;

// <editor-fold desc="Imports">
import com.example.Library.model.dto.ReferenceDTO;
import com.example.Library.service.interfaces.ReferenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
// </editor-fold>

@RestController
@RequestMapping("/api/references")
public class ReferenceController {

    // <editor-fold desc="Injected Dependencies">
    @Autowired private ReferenceService referenceService;
    @Autowired private ModelMapper mapper;
    // </editor-fold>

    // <editor-fold desc="End Points">
    @PostMapping
    public ResponseEntity<ReferenceDTO> add(@RequestBody DTO.ReferenceRecord referenceRecord) {
        ReferenceDTO referenceDTO = mapper.map(referenceRecord, ReferenceDTO.class);
        ReferenceDTO created = referenceService.add(referenceDTO);
        URI location = URI.create("/api/references/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReferenceDTO> getById(@PathVariable Long id) {
        try {
            ReferenceDTO reference = referenceService.getById(id);
            return ResponseEntity.ok(reference);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ReferenceDTO>> getAll() {
        List<ReferenceDTO> references = referenceService.getAll();
        return ResponseEntity.ok(references);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReferenceDTO> update(@PathVariable Long id, @RequestBody DTO.ReferenceRecord referenceRecord) {
        try {
            ReferenceDTO referenceDTO = mapper.map(referenceRecord, ReferenceDTO.class);
            ReferenceDTO updated = referenceService.update(id, referenceDTO);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            referenceService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        referenceService.deleteAll();
        return ResponseEntity.noContent().build();
    }
    // </editor-fold>
}
