package com.example.Library.controller;

// <editor-fold desc="Imports">
import com.example.Library.model.dto.BorrowDetailDTO;
import com.example.Library.service.interfaces.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
// </editor-fold>

@RestController
@RequestMapping("/api/")
public class BorrowController {

    // <editor-fold desc="Injected Dependencies">
    @Autowired private BorrowService borrowService;
    // </editor-fold>

    // <editor-fold desc="End Points">
    @PostMapping("borrow/{assetId}")
    public ResponseEntity<?> borrowAsset(@PathVariable Long assetId) {
        try {
            BorrowDetailDTO result = borrowService.borrowAsset(assetId);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("return/{assetId}")
    public ResponseEntity<?> returnAsset(@PathVariable Long assetId) {
        try {
            BorrowDetailDTO result = borrowService.returnAsset(assetId);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("borrow-detail/getAll")
    public ResponseEntity<List<BorrowDetailDTO>> getMyBorrowDetails() {
        List<BorrowDetailDTO> list = borrowService.getBorrowDetailsOfCurrentUser();
        return ResponseEntity.ok(list);
    }

    @GetMapping("borrow-detail/getAll/{assetId}")
    public ResponseEntity<List<BorrowDetailDTO>> getBorrowDetailsOfAsset(@PathVariable Long assetId) {
        List<BorrowDetailDTO> list = borrowService.getBorrowDetailsOfAsset(assetId);
        return ResponseEntity.ok(list);
    }
    // </editor-fold>
}
