package com.park.park.services.implementations;

import com.park.park.dto.TypyBiletowDTO;
import com.park.park.entities.TypyBiletowEntity;
import com.park.park.repositories.TypyBiletowRepository;
import com.park.park.responses.ModelResponse;
import com.park.park.services.TypyBiletowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypyBiletowServiceImpl implements TypyBiletowService {
    private final TypyBiletowRepository typyBiletowRepository;
    @Autowired
    public TypyBiletowServiceImpl(TypyBiletowRepository typyBiletowRepository) {
        this.typyBiletowRepository = typyBiletowRepository;
    }

    @Override
    public ModelResponse<TypyBiletowDTO> getAllTypyBiletow(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<TypyBiletowEntity> typyBiletowEntities = typyBiletowRepository.findAll(pageable);
        List<TypyBiletowEntity> typyBiletowEntityList = typyBiletowEntities.getContent();
        List<TypyBiletowDTO> content = typyBiletowEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<TypyBiletowDTO> typyBiletowResponse = mapToResponse(typyBiletowEntities);
        typyBiletowResponse.setContent(content);

        return typyBiletowResponse;
    }

    @Override
    public TypyBiletowDTO getTypyBiletowById(long idTypuBiletu) {
        TypyBiletowEntity typyBiletowEntity = typyBiletowRepository.findById(idTypuBiletu)
                .orElseThrow(() -> new RuntimeException("Nie ma typu biletu o zadanym id"));
        return mapToDTO(typyBiletowEntity);
    }

    @Override
    public TypyBiletowDTO createTypyBiletow(TypyBiletowDTO typyBiletowDTO) {
        TypyBiletowEntity typyBiletowEntity = mapToEntity(typyBiletowDTO);
        TypyBiletowEntity finalTypyBiletowEntity = typyBiletowRepository.save(typyBiletowEntity);

        return mapToDTO(finalTypyBiletowEntity);
    }

    @Override
    public TypyBiletowDTO updateTypyBiletow(TypyBiletowDTO typyBiletowDTO, long idTypuBiletu) {
        TypyBiletowEntity typyBiletowEntity = typyBiletowRepository.findById(idTypuBiletu)
                .orElseThrow(() -> new RuntimeException("Nie ma typu biletu o zadanym id"));

        if (typyBiletowDTO.getStandard() != null) typyBiletowEntity.setStandard(typyBiletowDTO.getStandard());
        if (typyBiletowDTO.getCena() != -1) typyBiletowEntity.setCena(typyBiletowDTO.getCena());
        if (typyBiletowDTO.getUlga() != null) typyBiletowEntity.setUlga(typyBiletowDTO.getUlga());
        if (typyBiletowDTO.getOpisTypuBiletu() != null)
            typyBiletowEntity.setOpisTypuBiletu(typyBiletowDTO.getOpisTypuBiletu());

        TypyBiletowEntity updatedTypyBiletow = typyBiletowRepository.save(typyBiletowEntity);
        return mapToDTO(updatedTypyBiletow);
    }

    @Override
    public void deleteTypyBiletow(long idTypuBiletu) {
        TypyBiletowEntity typyBiletowEntity = typyBiletowRepository.findById(idTypuBiletu)
                .orElseThrow(() -> new RuntimeException("Nie ma typu biletu o zadanym id"));
        typyBiletowRepository.delete(typyBiletowEntity);
    }

    private TypyBiletowDTO mapToDTO(TypyBiletowEntity typyBiletowEntity) {
        TypyBiletowDTO typyBiletowDTO = new TypyBiletowDTO();

        typyBiletowDTO.setIdTypuBiletu(typyBiletowEntity.getIdTypuBiletu());
        typyBiletowDTO.setStandard(typyBiletowEntity.getStandard());
        typyBiletowDTO.setCena(typyBiletowEntity.getCena());
        typyBiletowDTO.setUlga(typyBiletowEntity.getUlga());
        typyBiletowDTO.setOpisTypuBiletu(typyBiletowEntity.getOpisTypuBiletu());

        return typyBiletowDTO;
    }

    private TypyBiletowEntity mapToEntity(TypyBiletowDTO typyBiletowDTO) {
        TypyBiletowEntity typyBiletowEntity = new TypyBiletowEntity();

        typyBiletowEntity.setStandard(typyBiletowDTO.getStandard());
        typyBiletowEntity.setCena(typyBiletowDTO.getCena());
        typyBiletowEntity.setUlga(typyBiletowDTO.getUlga());
        typyBiletowEntity.setOpisTypuBiletu(typyBiletowDTO.getOpisTypuBiletu());

        return typyBiletowEntity;
    }

    private ModelResponse<TypyBiletowDTO> mapToResponse(Page<TypyBiletowEntity> typyBiletowEntities) {
        ModelResponse<TypyBiletowDTO> typyBiletowResponse = new ModelResponse<>();
        typyBiletowResponse.setTotalPages(typyBiletowEntities.getTotalPages());
        typyBiletowResponse.setTotalElements(typyBiletowEntities.getTotalElements());
        typyBiletowResponse.setPageNo(typyBiletowEntities.getNumber() + 1);
        typyBiletowResponse.setPageSize(typyBiletowEntities.getSize());
        typyBiletowResponse.setLast(typyBiletowEntities.isLast());
        return typyBiletowResponse;
    }
}
