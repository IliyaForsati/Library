package com.example.Library.service.implementation;

// <editor-fold desc="Imports">
import com.example.Library.model.dto.BorrowDetailDTO;
import com.example.Library.model.entity.Asset;
import com.example.Library.model.entity.BorrowDetail;
import com.example.Library.model.entity.User;
import com.example.Library.repository.AssetRepository;
import com.example.Library.repository.BorrowDetailRepository;
import com.example.Library.service.interfaces.BorrowService;
import com.example.Library.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
// </editor-fold>

@Service
public class BorrowServiceIMP implements BorrowService {

    // <editor-fold desc="Injected Dependencies">
    @Autowired private BorrowDetailRepository repository;

    @Autowired private UserService userService;

    @Autowired private AssetRepository assetRepository;

    @Autowired private ModelMapper mapper;
    // </editor-fold>

    // <editor-fold desc="Methods Implementations">
    @Override
    public BorrowDetailDTO borrowAsset(Long assetId) throws IllegalArgumentException {
        BorrowDetail entity = new BorrowDetail();

        User user = mapper.map(userService.getCurrentUser(), User.class);

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("Asset with id " + assetId + "not found"));

        if (!asset.getBorrowDetailList().isEmpty() && asset.getBorrowDetailList().getLast().getReturnDate() != null)
            throw new IllegalArgumentException("Asset with id " + assetId + "is borrowed");

        entity.setUser(user);
        entity.setAsset(asset);
        entity.setBorrowDate(LocalDate.now());
        entity.setReturnDate(null);

        BorrowDetail borrowDetail = repository.save(entity);
        return mapper.map(borrowDetail, BorrowDetailDTO.class);
    }

    @Override
    public BorrowDetailDTO returnAsset(Long assetId) throws IllegalArgumentException {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("Asset with id " + assetId + "not found"));

        BorrowDetail borrowDetail = asset.getBorrowDetailList().getLast();

        if (!Objects.equals(borrowDetail.getUser().getId(), userService.getCurrentUser().getId()))
            throw new IllegalArgumentException("you don't have permission to return this asset");

        if (borrowDetail.getReturnDate() != null)
            throw new IllegalArgumentException("Asset with id " + assetId + "is not borrowed");

        borrowDetail.setReturnDate(LocalDate.now());
        borrowDetail = repository.save(borrowDetail);

        return mapper.map(borrowDetail, BorrowDetailDTO.class);
    }

    @Override
    public List<BorrowDetailDTO> getBorrowDetailsOfCurrentUser() {
        return repository.findByUserId(userService.getCurrentUser().getId()).stream()
                .map(borrowDetail -> mapper.map(borrowDetail, BorrowDetailDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowDetailDTO> getBorrowDetailsOfAsset(Long assetId) {
        return repository.findByAssetIdAndUserId(assetId, userService.getCurrentUser().getId()).stream()
                .map(borrowDetail -> mapper.map(borrowDetail, BorrowDetailDTO.class))
                .collect(Collectors.toList());
    }
    // </editor-fold>
}
