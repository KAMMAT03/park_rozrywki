package com.park.park.services.implementations;

import com.park.park.dto.AtrakcjeDTO;
import com.park.park.entities.AtrakcjeEntity;
import com.park.park.entities.GastronomieEntity;
import com.park.park.entities.KolejkiGorskieEntity;
import com.park.park.repositories.AtrakcjeRepository;
import com.park.park.repositories.GastronomieRepository;
import com.park.park.repositories.KolejkiGorskieRepository;
import com.park.park.responses.ModelResponse;
import com.park.park.services.AtrakcjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AtrakcjeServiceImpl implements AtrakcjeService {
    private final AtrakcjeRepository atrakcjeRepository;
    private final KolejkiGorskieRepository kolejkiGorskieRepository;
    private final GastronomieRepository gastronomieRepository;

    @Autowired
    public AtrakcjeServiceImpl(AtrakcjeRepository atrakcjeRepository, KolejkiGorskieRepository kolejkiGorskieRepository, GastronomieRepository gastronomieRepository) {
        this.atrakcjeRepository = atrakcjeRepository;
        this.kolejkiGorskieRepository = kolejkiGorskieRepository;
        this.gastronomieRepository = gastronomieRepository;
    }

    @Override
    public ModelResponse<AtrakcjeDTO> getAllAtrakcje(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<AtrakcjeEntity> atrakcjeEntities = atrakcjeRepository.findAll(pageable);
        List<AtrakcjeEntity> atrakcjeEntityList = atrakcjeEntities.getContent();
        List<AtrakcjeDTO> content = atrakcjeEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<AtrakcjeDTO> atrakcjeResponse = mapToResponse(atrakcjeEntities);
        atrakcjeResponse.setContent(content);

        return atrakcjeResponse;
    }

    @Override
    public ModelResponse<AtrakcjeDTO> getOnlyAtrakcje(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<String> excluded = new ArrayList<>();
        excluded.add("Kolejka górska");
        excluded.add("Gastronomia");
        Page<AtrakcjeEntity> atrakcjeEntities = atrakcjeRepository.findAllByTypAtrakcjiIsNotIn(excluded,pageable);
        List<AtrakcjeEntity> atrakcjeEntityList = atrakcjeEntities.getContent();
        List<AtrakcjeDTO> content = atrakcjeEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<AtrakcjeDTO> atrakcjeResponse = mapToResponse(atrakcjeEntities);
        atrakcjeResponse.setContent(content);

        return atrakcjeResponse;
    }

    @Override
    public AtrakcjeDTO getAtrakcjeById(long idAtrakcji) {
        AtrakcjeEntity atrakcjeEntity = atrakcjeRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma atrakcji o zadanym id"));

        return mapToDTO(atrakcjeEntity);
    }

    @Override
    public AtrakcjeDTO createAtrakcje(AtrakcjeDTO atrakcjeDTO) {
        AtrakcjeEntity atrakcjeEntity = mapToEntity(atrakcjeDTO);
        AtrakcjeEntity finalAtrakcjeEntity = atrakcjeRepository.save(atrakcjeEntity);
        return mapToDTO(finalAtrakcjeEntity);
    }

    @Override
    public AtrakcjeDTO updateAtrakcje(AtrakcjeDTO atrakcjeDTO, long idAtrakcji) {
        AtrakcjeEntity atrakcjeEntity = atrakcjeRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma atrakcji o zadanym id"));

        if (atrakcjeDTO.getNazwaAtrakcji() != null) atrakcjeEntity.setNazwaAtrakcji(atrakcjeDTO.getNazwaAtrakcji());
        if (atrakcjeDTO.getTypAtrakcji() != null) atrakcjeEntity.setTypAtrakcji(atrakcjeDTO.getTypAtrakcji());
        if (atrakcjeDTO.getOpisAtrakcji() != null) atrakcjeEntity.setOpisAtrakcji(atrakcjeDTO.getOpisAtrakcji());
        if (atrakcjeDTO.getImageUrl() != null) atrakcjeEntity.setImageUrl(atrakcjeDTO.getImageUrl());

        AtrakcjeEntity updatedAtrakcje = atrakcjeRepository.save(atrakcjeEntity);

        return mapToDTO(updatedAtrakcje);
    }

    @Override
    public void deleteAtrakcje(long idAtrakcji) {
        AtrakcjeEntity atrakcjeEntity = atrakcjeRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma atrakcji o zadanym id"));

        if (atrakcjeEntity.getTypAtrakcji().equals("Kolejka górska")){
            KolejkiGorskieEntity kolejkiGorskieEntity = kolejkiGorskieRepository.findById(idAtrakcji)
                    .orElseThrow(() -> new RuntimeException("Nie ma kolejki o zadanym id"));

            kolejkiGorskieRepository.delete(kolejkiGorskieEntity);
        } else if (atrakcjeEntity.getTypAtrakcji().equals("Gastronomia")){
            GastronomieEntity gastronomieEntity = gastronomieRepository.findById(idAtrakcji)
                    .orElseThrow(() -> new RuntimeException("Nie ma gastronomii o zadanym id"));

            gastronomieRepository.delete(gastronomieEntity);
        }

        atrakcjeRepository.delete(atrakcjeEntity);
    }

    private AtrakcjeEntity mapToEntity(AtrakcjeDTO atrakcjeDTO){
        AtrakcjeEntity atrakcjeEntity = new AtrakcjeEntity();
        atrakcjeEntity.setNazwaAtrakcji(atrakcjeDTO.getNazwaAtrakcji());
        atrakcjeEntity.setTypAtrakcji(atrakcjeDTO.getTypAtrakcji());
        atrakcjeEntity.setOpisAtrakcji(atrakcjeDTO.getOpisAtrakcji());
        atrakcjeEntity.setIdParkuRozrywki(atrakcjeDTO.getIdParkuRozrywki());
        atrakcjeEntity.setImageUrl(atrakcjeDTO.getImageUrl());
        return atrakcjeEntity;
    }

    private AtrakcjeDTO mapToDTO(AtrakcjeEntity atrakcjeEntity){
        AtrakcjeDTO atrakcjeDTO = new AtrakcjeDTO();
        atrakcjeDTO.setIdAtrakcji(atrakcjeEntity.getIdAtrakcji());
        atrakcjeDTO.setNazwaAtrakcji(atrakcjeEntity.getNazwaAtrakcji());
        atrakcjeDTO.setTypAtrakcji(atrakcjeEntity.getTypAtrakcji());
        atrakcjeDTO.setOpisAtrakcji(atrakcjeEntity.getOpisAtrakcji());
        atrakcjeDTO.setIdParkuRozrywki(atrakcjeEntity.getIdParkuRozrywki());
        atrakcjeDTO.setImageUrl(atrakcjeEntity.getImageUrl());
        return atrakcjeDTO;
    }

    private ModelResponse<AtrakcjeDTO> mapToResponse(Page<AtrakcjeEntity> atrakcjeEntities){
        ModelResponse<AtrakcjeDTO> atrakcjeResponse = new ModelResponse<>();
        atrakcjeResponse.setPageNo(atrakcjeEntities.getNumber() + 1);
        atrakcjeResponse.setPageSize(atrakcjeEntities.getSize());
        atrakcjeResponse.setTotalElements(atrakcjeEntities.getTotalElements());
        atrakcjeResponse.setTotalPages(atrakcjeEntities.getTotalPages());
        atrakcjeResponse.setLast(atrakcjeEntities.isLast());
        return atrakcjeResponse;
    }
}
