package com.example.Library.controller;

import com.example.Library.model.dto.BorrowDetailDTO;
import com.example.Library.service.interfaces.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public ResponseEntity<BorrowDetailDTO> borrowAsset(@RequestBody BorrowDetailDTO dto) {
        BorrowDetailDTO result = borrowService.borrowAsset(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{assetId}/return")
    public ResponseEntity<BorrowDetailDTO> returnAsset(@PathVariable Long assetId) {
        BorrowDetailDTO result = borrowService.returnAsset(assetId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/me")
    public ResponseEntity<List<BorrowDetailDTO>> getMyBorrowDetails() {
        List<BorrowDetailDTO> list = borrowService.getBorrowDetailsOfCurrentUser();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<BorrowDetailDTO>> getBorrowDetailsOfAsset(@PathVariable Long assetId) {
        List<BorrowDetailDTO> list = borrowService.getBorrowDetailsOfAsset(assetId);
        return ResponseEntity.ok(list);
    }
}
