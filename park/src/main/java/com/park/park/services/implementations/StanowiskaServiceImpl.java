package com.park.park.services.implementations;

import com.park.park.dto.StanowiskaDTO;
import com.park.park.entities.StanowiskaEntity;
import com.park.park.repositories.StanowiskaRepository;
import com.park.park.responses.ModelResponse;
import com.park.park.services.StanowiskaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StanowiskaServiceImpl implements StanowiskaService {
    private final StanowiskaRepository stanowiskaRepository;
    @Autowired
    public StanowiskaServiceImpl(StanowiskaRepository stanowiskaRepository) {
        this.stanowiskaRepository = stanowiskaRepository;
    }

    @Override
    public ModelResponse<StanowiskaDTO> getAllStanowiska(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<StanowiskaEntity> stanowiskaEntities = stanowiskaRepository.findAll(pageable);
        List<StanowiskaEntity> stanowiskaEntityList = stanowiskaEntities.getContent();
        List<StanowiskaDTO> content = stanowiskaEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<StanowiskaDTO> stanowiskaResponse = mapToResponse(stanowiskaEntities);
        stanowiskaResponse.setContent(content);

        return stanowiskaResponse;
    }

    @Override
    public StanowiskaDTO getStanowiskaById(long idStanowiska) {
        StanowiskaEntity stanowiskaEntity = stanowiskaRepository.findById(idStanowiska)
                .orElseThrow(() -> new RuntimeException("Nie ma stanowiska o zadanym id"));

        return mapToDTO(stanowiskaEntity);
    }

    @Override
    public StanowiskaDTO createStanowiska(StanowiskaDTO stanowiskaDTO) {
        StanowiskaEntity stanowiskaEntity = mapToEntity(stanowiskaDTO);
        StanowiskaEntity finalStanowiskaEntity = stanowiskaRepository.save(stanowiskaEntity);

        return mapToDTO(finalStanowiskaEntity);
    }

    @Override
    public StanowiskaDTO updateStanowiska(StanowiskaDTO stanowiskaDTO, long idStanowiska) {
        StanowiskaEntity stanowiskaEntity = stanowiskaRepository.findById(idStanowiska)
                .orElseThrow(() -> new RuntimeException("Nie ma stanowiska o zadanym id"));

        if (stanowiskaDTO.getNazwaStanowiska() != null)
            stanowiskaEntity.setNazwaStanowiska(stanowiskaDTO.getNazwaStanowiska());
        if (stanowiskaDTO.getWynagrodzenie() != -1)
            stanowiskaEntity.setWynagrodzenie(stanowiskaDTO.getWynagrodzenie());
        if (stanowiskaDTO.getGodzinyPracy() != null)
            stanowiskaEntity.setGodzinyPracy(stanowiskaDTO.getGodzinyPracy());
        if (stanowiskaDTO.getZakresObowiazkow() != null)
            stanowiskaEntity.setZakresObowiazkow(stanowiskaDTO.getZakresObowiazkow());

        StanowiskaEntity updatedStanowiska = stanowiskaRepository.save(stanowiskaEntity);

        return mapToDTO(updatedStanowiska);
    }

    @Override
    public void deleteStanowiska(long idStanowiska) {
        StanowiskaEntity stanowiskaEntity = stanowiskaRepository.findById(idStanowiska)
                .orElseThrow(() -> new RuntimeException("Nie ma stanowiska o zadanym id"));

        stanowiskaRepository.delete(stanowiskaEntity);
    }

    private StanowiskaDTO mapToDTO(StanowiskaEntity stanowiskaEntity) {
        StanowiskaDTO stanowiskaDTO = new StanowiskaDTO();
        stanowiskaDTO.setIdStanowiska(stanowiskaEntity.getIdStanowiska());
        stanowiskaDTO.setNazwaStanowiska(stanowiskaEntity.getNazwaStanowiska());
        stanowiskaDTO.setWynagrodzenie(stanowiskaEntity.getWynagrodzenie());
        stanowiskaDTO.setGodzinyPracy(stanowiskaEntity.getGodzinyPracy());
        stanowiskaDTO.setZakresObowiazkow(stanowiskaEntity.getZakresObowiazkow());
        return stanowiskaDTO;
    }

    private StanowiskaEntity mapToEntity(StanowiskaDTO stanowiskaDTO) {
        StanowiskaEntity stanowiskaEntity = new StanowiskaEntity();
        stanowiskaEntity.setNazwaStanowiska(stanowiskaDTO.getNazwaStanowiska());
        stanowiskaEntity.setWynagrodzenie(stanowiskaDTO.getWynagrodzenie());
        stanowiskaEntity.setGodzinyPracy(stanowiskaDTO.getGodzinyPracy());
        stanowiskaEntity.setZakresObowiazkow(stanowiskaDTO.getZakresObowiazkow());
        return stanowiskaEntity;
    }

    private ModelResponse<StanowiskaDTO> mapToResponse(Page<StanowiskaEntity> stanowiskaEntities) {
        ModelResponse<StanowiskaDTO> stanowiskaResponse = new ModelResponse<>();
        stanowiskaResponse.setTotalPages(stanowiskaEntities.getTotalPages());
        stanowiskaResponse.setTotalElements(stanowiskaEntities.getTotalElements());
        stanowiskaResponse.setPageNo(stanowiskaEntities.getNumber() + 1);
        stanowiskaResponse.setPageSize(stanowiskaEntities.getSize());
        stanowiskaResponse.setLast(stanowiskaEntities.isLast());
        return stanowiskaResponse;
    }
}
