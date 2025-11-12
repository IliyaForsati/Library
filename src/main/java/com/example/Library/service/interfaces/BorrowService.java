package com.example.Library.service.interfaces;

import com.example.Library.model.dto.BorrowDetailDTO;

import java.util.List;

public interface BorrowService {
    BorrowDetailDTO borrowAsset(BorrowDetailDTO borrowDetailDTO);
    BorrowDetailDTO returnAsset(Long assetId);
    List<BorrowDetailDTO> getBorrowDetailsOfCurrentUser();
    List<BorrowDetailDTO> getBorrowDetailsOfAsset(Long assetId);
}
