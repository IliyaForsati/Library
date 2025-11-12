package com.example.Library.service.implementation;

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

@Service
public class BorrowServiceIMP implements BorrowService {

    @Autowired
    private BorrowDetailRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BorrowDetailDTO borrowAsset(BorrowDetailDTO borrowDetailDTO) {
        BorrowDetail entity = mapper.map(borrowDetailDTO, BorrowDetail.class);

        User user = mapper.map(userService.getCurrentUser(), User.class);

        Asset asset = assetRepository.findById(borrowDetailDTO.getAssetID())
                .orElseThrow(() -> new IllegalArgumentException("Asset with id " + borrowDetailDTO.getAssetID() + "not found"));

        entity.setUser(user);
        entity.setAsset(asset);

        BorrowDetail borrowDetail = repository.save(entity);
        return mapper.map(borrowDetail, BorrowDetailDTO.class);
    }

    @Override
    public BorrowDetailDTO returnAsset(Long assetId) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new IllegalArgumentException("Asset with id " + assetId + "not found"));

        BorrowDetail borrowDetail = asset.getBorrowDetailList().getLast();

        if (!Objects.equals(borrowDetail.getUser().getId(), userService.getCurrentUser().getId()))
            throw new IllegalArgumentException("you don't have permission to return this asset");

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
}
