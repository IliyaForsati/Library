package com.example.Library.controller;

// <editor-fold desc="Imports">
import com.example.Library.model.dto.MagazineDTO;
import com.example.Library.service.interfaces.MagazineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
// </editor-fold>

@RestController
@RequestMapping("/api/magazines")
public class MagazineController {

    // <editor-fold desc="Injected Dependencies">
    @Autowired private MagazineService magazineService;
    @Autowired private ModelMapper mapper;
    // </editor-fold>

    // <editor-fold desc="End Points">
    @PostMapping
    public ResponseEntity<MagazineDTO> add(@RequestBody DTO.MagazineRecord magazineRecord) {
        MagazineDTO magazineDTO = mapper.map(magazineRecord, MagazineDTO.class);
        MagazineDTO created = magazineService.add(magazineDTO);
        URI location = URI.create("/api/magazines/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MagazineDTO> getById(@PathVariable Long id) {
        try {
            MagazineDTO magazine = magazineService.getById(id);
            return ResponseEntity.ok(magazine);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MagazineDTO>> getAll() {
        List<MagazineDTO> magazines = magazineService.getAll();
        return ResponseEntity.ok(magazines);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MagazineDTO> update(@PathVariable Long id, @RequestBody DTO.MagazineRecord magazineRecord) {
        try {
            MagazineDTO magazineDTO = mapper.map(magazineRecord, MagazineDTO.class);
            MagazineDTO updated = magazineService.update(id, magazineDTO);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            magazineService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        magazineService.deleteAll();
        return ResponseEntity.noContent().build();
    }
    // </editor-fold>
}
