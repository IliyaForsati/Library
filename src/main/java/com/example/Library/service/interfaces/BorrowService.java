package com.example.Library.service.interfaces;

import com.example.Library.model.dto.BorrowDetailDTO;
import java.util.List;

public interface BorrowService {
    BorrowDetailDTO borrowAsset(Long assetId) throws IllegalArgumentException;
    BorrowDetailDTO returnAsset(Long assetId) throws IllegalArgumentException;
    List<BorrowDetailDTO> getBorrowDetailsOfCurrentUser();
    List<BorrowDetailDTO> getBorrowDetailsOfAsset(Long assetId);
}
