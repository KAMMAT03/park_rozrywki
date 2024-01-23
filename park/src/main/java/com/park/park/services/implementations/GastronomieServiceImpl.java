package com.park.park.services.implementations;

import com.park.park.dto.GastronomieDTO;
import com.park.park.entities.AtrakcjeEntity;
import com.park.park.entities.GastronomieEntity;
import com.park.park.repositories.AtrakcjeRepository;
import com.park.park.repositories.GastronomieRepository;
import com.park.park.responses.ModelResponse;
import com.park.park.services.GastronomieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastronomieServiceImpl implements GastronomieService {
    private final GastronomieRepository gastronomieRepository;
    private final AtrakcjeRepository atrakcjeRepository;

    @Autowired
    public GastronomieServiceImpl(AtrakcjeRepository atrakcjeRepository,
                                     GastronomieRepository gastronomieRepository) {
        this.atrakcjeRepository = atrakcjeRepository;
        this.gastronomieRepository = gastronomieRepository;
    }

    @Override
    public ModelResponse<GastronomieDTO> getAllGastronomie(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<GastronomieEntity> gastronomieEntities = gastronomieRepository.findAll(pageable);
        List<GastronomieEntity> gastronomieEntityList = gastronomieEntities.getContent();
        List<GastronomieDTO> content = gastronomieEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<GastronomieDTO> gastronomieResponse = mapToResponse(gastronomieEntities);
        gastronomieResponse.setContent(content);

        return gastronomieResponse;
    }

    @Override
    public GastronomieDTO getGastronomieById(long idAtrakcji) {
        GastronomieEntity gastronomieEntity = gastronomieRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma gastronomii o zadanym id"));

        return mapToDTO(gastronomieEntity);
    }

    @Override
    public GastronomieDTO createGastronomie(GastronomieDTO gastronomieDTO) {
        GastronomieEntity gastronomieEntity = mapToEntity(gastronomieDTO);
        GastronomieEntity finalGastronomieEntity = gastronomieRepository.save(gastronomieEntity);
        return mapToDTO(finalGastronomieEntity);
    }

    @Override
    public GastronomieDTO updateGastronomie(
            GastronomieDTO gastronomieDTO, long idAtrakcji) {
        GastronomieEntity gastronomieEntity = gastronomieRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma gastronomii o zadanym id"));

        AtrakcjeEntity atrakcjeEntity = atrakcjeRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma atrakcji odpowiadającej gastronomii o danym id"));

        if (gastronomieDTO.getNazwaAtrakcji() != null)
            atrakcjeEntity.setNazwaAtrakcji(gastronomieDTO.getNazwaAtrakcji());
        if (gastronomieDTO.getTypAtrakcji() != null)
            atrakcjeEntity.setTypAtrakcji(gastronomieDTO.getTypAtrakcji());
        if (gastronomieDTO.getOpisAtrakcji() != null)
            atrakcjeEntity.setOpisAtrakcji(gastronomieDTO.getOpisAtrakcji());
        if (gastronomieDTO.getImageUrl() != null)
            atrakcjeEntity.setImageUrl(gastronomieDTO.getImageUrl());

        atrakcjeRepository.save(atrakcjeEntity);

        if (gastronomieDTO.getGodzinyOtwarcia() != null) {
            gastronomieEntity.setGodzinyOtwarcia(gastronomieDTO.getGodzinyOtwarcia());
        }
        if (gastronomieDTO.getTyp() != null) {
            gastronomieEntity.setTyp(gastronomieDTO.getTyp());
        }
        if (gastronomieDTO.getLiczbaMiejsc() != -1) {
            gastronomieEntity.setLiczbaMiejsc(gastronomieDTO.getLiczbaMiejsc());
        }

        GastronomieEntity updatedGastronomie = gastronomieRepository.save(gastronomieEntity);

        return mapToDTO(updatedGastronomie);
    }

    @Override
    public void deleteGastronomie(long idAtrakcji) {
        GastronomieEntity gastronomieEntity = gastronomieRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma gastronomii o zadanym id"));
        gastronomieRepository.delete(gastronomieEntity);

        AtrakcjeEntity atrakcjeEntity = atrakcjeRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma atrakcji o zadanym id"));

        atrakcjeRepository.delete(atrakcjeEntity);
    }

    private GastronomieEntity mapToEntity(GastronomieDTO gastronomieDTO){
        GastronomieEntity gastronomieEntity = new GastronomieEntity();

        AtrakcjeEntity atrakcjeEntity = new AtrakcjeEntity();

        atrakcjeEntity.setNazwaAtrakcji(gastronomieDTO.getNazwaAtrakcji());
        atrakcjeEntity.setTypAtrakcji("Gastronomia");
        atrakcjeEntity.setOpisAtrakcji(gastronomieDTO.getOpisAtrakcji());
        atrakcjeEntity.setIdParkuRozrywki(1);
        atrakcjeEntity.setImageUrl(gastronomieDTO.getImageUrl());

        AtrakcjeEntity finalAtrakcjeEntity = atrakcjeRepository.save(atrakcjeEntity);

        gastronomieEntity.setIdAtrakcji(finalAtrakcjeEntity.getIdAtrakcji());
        gastronomieEntity.setTyp(gastronomieDTO.getTyp());
        gastronomieEntity.setGodzinyOtwarcia(gastronomieDTO.getGodzinyOtwarcia());
        gastronomieEntity.setLiczbaMiejsc(gastronomieDTO.getLiczbaMiejsc());

        return gastronomieEntity;
    }

    private GastronomieDTO mapToDTO(GastronomieEntity gastronomieEntity){
        GastronomieDTO gastronomieDTO = new GastronomieDTO();

        gastronomieDTO.setIdAtrakcji(gastronomieEntity.getIdAtrakcji());
        gastronomieDTO.setTyp(gastronomieEntity.getTyp());
        gastronomieDTO.setGodzinyOtwarcia(gastronomieEntity.getGodzinyOtwarcia());
        gastronomieDTO.setLiczbaMiejsc(gastronomieEntity.getLiczbaMiejsc());

        AtrakcjeEntity atrakcjeEntity = atrakcjeRepository.findById(gastronomieEntity.getIdAtrakcji())
                .orElseThrow(() -> new RuntimeException("Gastronomia nie odpowiada żadnej atrakcji"));

        gastronomieDTO.setNazwaAtrakcji(atrakcjeEntity.getNazwaAtrakcji());
        gastronomieDTO.setTypAtrakcji(atrakcjeEntity.getTypAtrakcji());
        gastronomieDTO.setOpisAtrakcji(atrakcjeEntity.getOpisAtrakcji());
        gastronomieDTO.setImageUrl(atrakcjeEntity.getImageUrl());

        return gastronomieDTO;
    }

    private ModelResponse<GastronomieDTO> mapToResponse(Page<GastronomieEntity> gastronomieEntities){
        ModelResponse<GastronomieDTO> gastronomieResponse = new ModelResponse<>();
        gastronomieResponse.setPageNo(gastronomieEntities.getNumber() + 1);
        gastronomieResponse.setPageSize(gastronomieEntities.getSize());
        gastronomieResponse.setTotalElements(gastronomieEntities.getTotalElements());
        gastronomieResponse.setTotalPages(gastronomieEntities.getTotalPages());
        gastronomieResponse.setLast(gastronomieEntities.isLast());
        return gastronomieResponse;
    }
}
