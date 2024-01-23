package com.park.park.services.implementations;

import com.park.park.dto.KolejkiGorskieDTO;
import com.park.park.entities.AtrakcjeEntity;
import com.park.park.entities.KolejkiGorskieEntity;
import com.park.park.repositories.AtrakcjeRepository;
import com.park.park.repositories.KolejkiGorskieRepository;
import com.park.park.responses.ModelResponse;
import com.park.park.services.KolejkiGorskieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KolejkiGorskieServiceImpl implements KolejkiGorskieService {
    private final KolejkiGorskieRepository kolejkiGorskieRepository;
    private final AtrakcjeRepository atrakcjeRepository;

    @Autowired
    public KolejkiGorskieServiceImpl(AtrakcjeRepository atrakcjeRepository,
                                     KolejkiGorskieRepository kolejkiGorskieRepository) {
        this.atrakcjeRepository = atrakcjeRepository;
        this.kolejkiGorskieRepository = kolejkiGorskieRepository;
    }

    @Override
    public ModelResponse<KolejkiGorskieDTO> getAllKolejkiGorskie(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<KolejkiGorskieEntity> kolejkiGorskieEntities = kolejkiGorskieRepository.findAll(pageable);
        List<KolejkiGorskieEntity> kolejkiGorskieEntityList = kolejkiGorskieEntities.getContent();
        List<KolejkiGorskieDTO> content = kolejkiGorskieEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<KolejkiGorskieDTO> kolejkiGorskieResponse = mapToResponse(kolejkiGorskieEntities);
        kolejkiGorskieResponse.setContent(content);

        return kolejkiGorskieResponse;
    }

    @Override
    public KolejkiGorskieDTO getKolejkiGorskieById(long idAtrakcji) {
        KolejkiGorskieEntity kolejkiGorskieEntity = kolejkiGorskieRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma kolejki o zadanym id"));

        return mapToDTO(kolejkiGorskieEntity);
    }

    @Override
    public KolejkiGorskieDTO createKolejkiGorskie(KolejkiGorskieDTO kolejkiGorskieDTO) {
        KolejkiGorskieEntity kolejkiGorskieEntity = mapToEntity(kolejkiGorskieDTO);
        KolejkiGorskieEntity finalKolejkiGorskieEntity = kolejkiGorskieRepository.save(kolejkiGorskieEntity);
        return mapToDTO(finalKolejkiGorskieEntity);
    }

    @Override
    public KolejkiGorskieDTO updateKolejkiGorskie(
            KolejkiGorskieDTO kolejkiGorskieDTO, long idAtrakcji) {
        KolejkiGorskieEntity kolejkiGorskieEntity = kolejkiGorskieRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma kolejki o zadanym id"));

        AtrakcjeEntity atrakcjeEntity = atrakcjeRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma atrakcji odpowiadającej kolejce o danym id"));

        if (kolejkiGorskieDTO.getNazwaAtrakcji() != null)
            atrakcjeEntity.setNazwaAtrakcji(kolejkiGorskieDTO.getNazwaAtrakcji());
        if (kolejkiGorskieDTO.getTypAtrakcji() != null)
            atrakcjeEntity.setTypAtrakcji(kolejkiGorskieDTO.getTypAtrakcji());
        if (kolejkiGorskieDTO.getOpisAtrakcji() != null)
            atrakcjeEntity.setOpisAtrakcji(kolejkiGorskieDTO.getOpisAtrakcji());
        if (kolejkiGorskieDTO.getImageUrl() != null)
            atrakcjeEntity.setImageUrl(kolejkiGorskieDTO.getImageUrl());

        atrakcjeRepository.save(atrakcjeEntity);

        if (kolejkiGorskieDTO.getCzasOczekiwania() != -1) {
            kolejkiGorskieEntity.setCzasOczekiwania(kolejkiGorskieDTO.getCzasOczekiwania());
        }
        if (kolejkiGorskieDTO.getMaksymalnaWysokosc() != -1) {
            kolejkiGorskieEntity.setMaksymalnaWysokosc(kolejkiGorskieDTO.getMaksymalnaWysokosc());
        }
        if (kolejkiGorskieDTO.getMinimalnyWzrost() != -1) {
            kolejkiGorskieEntity.setMinimalnyWzrost(kolejkiGorskieDTO.getMinimalnyWzrost());
        }
        if (kolejkiGorskieDTO.getPojemnoscWagonika() != -1) {
            kolejkiGorskieEntity.setPojemnoscWagonika(kolejkiGorskieDTO.getPojemnoscWagonika());
        }
        if (kolejkiGorskieDTO.getMinimialnyWiek() != -1) {
            kolejkiGorskieEntity.setMinimialnyWiek(kolejkiGorskieDTO.getMinimialnyWiek());
        }
        if (kolejkiGorskieDTO.getSredniaPredkosc() != -1) {
            kolejkiGorskieEntity.setSredniaPredkosc(kolejkiGorskieDTO.getSredniaPredkosc());
        }
        if (kolejkiGorskieDTO.getCzasJazdy() != -1) {
            kolejkiGorskieEntity.setCzasJazdy(kolejkiGorskieDTO.getCzasJazdy());
        }
        if (kolejkiGorskieDTO.getDataOstatniegoPrzegladu() != null) {
            kolejkiGorskieEntity.setDataOstatniegoPrzegladu(kolejkiGorskieDTO.getDataOstatniegoPrzegladu());
        }

        KolejkiGorskieEntity updatedKolejkiGorskie = kolejkiGorskieRepository.save(kolejkiGorskieEntity);

        return mapToDTO(updatedKolejkiGorskie);
    }

    @Override
    public void deleteKolejkiGorskie(long idAtrakcji) {
        KolejkiGorskieEntity kolejkiGorskieEntity = kolejkiGorskieRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma kolejki o zadanym id"));

        kolejkiGorskieRepository.delete(kolejkiGorskieEntity);

        AtrakcjeEntity atrakcjeEntity = atrakcjeRepository.findById(idAtrakcji)
                .orElseThrow(() -> new RuntimeException("Nie ma atrakcji o zadanym id"));

        atrakcjeRepository.delete(atrakcjeEntity);
    }

    private KolejkiGorskieEntity mapToEntity(KolejkiGorskieDTO kolejkiGorskieDTO){
        KolejkiGorskieEntity kolejkiGorskieEntity = new KolejkiGorskieEntity();

        AtrakcjeEntity atrakcjeEntity = new AtrakcjeEntity();

        atrakcjeEntity.setNazwaAtrakcji(kolejkiGorskieDTO.getNazwaAtrakcji());
        atrakcjeEntity.setTypAtrakcji("Kolejka górska");
        atrakcjeEntity.setOpisAtrakcji(kolejkiGorskieDTO.getOpisAtrakcji());
        atrakcjeEntity.setIdParkuRozrywki(1);
        atrakcjeEntity.setImageUrl(kolejkiGorskieDTO.getImageUrl());

        AtrakcjeEntity finalAtrakcjeEntity = atrakcjeRepository.save(atrakcjeEntity);

        kolejkiGorskieEntity.setIdAtrakcji(finalAtrakcjeEntity.getIdAtrakcji());
        kolejkiGorskieEntity.setCzasJazdy(kolejkiGorskieDTO.getCzasJazdy());
        kolejkiGorskieEntity.setCzasOczekiwania(kolejkiGorskieDTO.getCzasOczekiwania());
        kolejkiGorskieEntity.setMinimalnyWzrost(kolejkiGorskieDTO.getMinimalnyWzrost());
        kolejkiGorskieEntity.setMaksymalnaWysokosc(kolejkiGorskieDTO.getMaksymalnaWysokosc());
        kolejkiGorskieEntity.setPojemnoscWagonika(kolejkiGorskieDTO.getPojemnoscWagonika());
        kolejkiGorskieEntity.setDataOstatniegoPrzegladu(kolejkiGorskieDTO.getDataOstatniegoPrzegladu());
        kolejkiGorskieEntity.setSredniaPredkosc(kolejkiGorskieDTO.getSredniaPredkosc());
        kolejkiGorskieEntity.setMinimialnyWiek(kolejkiGorskieDTO.getMinimialnyWiek());

        return kolejkiGorskieEntity;
    }

    private KolejkiGorskieDTO mapToDTO(KolejkiGorskieEntity kolejkiGorskieEntity){
        KolejkiGorskieDTO kolejkiGorskieDTO = new KolejkiGorskieDTO();

        kolejkiGorskieDTO.setIdAtrakcji(kolejkiGorskieEntity.getIdAtrakcji());
        kolejkiGorskieDTO.setCzasJazdy(kolejkiGorskieEntity.getCzasJazdy());
        kolejkiGorskieDTO.setCzasOczekiwania(kolejkiGorskieEntity.getCzasOczekiwania());
        kolejkiGorskieDTO.setMinimalnyWzrost(kolejkiGorskieEntity.getMinimalnyWzrost());
        kolejkiGorskieDTO.setMaksymalnaWysokosc(kolejkiGorskieEntity.getMaksymalnaWysokosc());
        kolejkiGorskieDTO.setPojemnoscWagonika(kolejkiGorskieEntity.getPojemnoscWagonika());
        kolejkiGorskieDTO.setDataOstatniegoPrzegladu(kolejkiGorskieEntity.getDataOstatniegoPrzegladu());
        kolejkiGorskieDTO.setSredniaPredkosc(kolejkiGorskieEntity.getSredniaPredkosc());
        kolejkiGorskieDTO.setMinimialnyWiek(kolejkiGorskieEntity.getMinimialnyWiek());

        AtrakcjeEntity atrakcjeEntity = atrakcjeRepository.findById(kolejkiGorskieEntity.getIdAtrakcji())
                .orElseThrow(() -> new RuntimeException("Kolejka nie odpowiada żadnej atrakcji"));

        kolejkiGorskieDTO.setNazwaAtrakcji(atrakcjeEntity.getNazwaAtrakcji());
        kolejkiGorskieDTO.setTypAtrakcji(atrakcjeEntity.getTypAtrakcji());
        kolejkiGorskieDTO.setOpisAtrakcji(atrakcjeEntity.getOpisAtrakcji());
        kolejkiGorskieDTO.setImageUrl(atrakcjeEntity.getImageUrl());

        return kolejkiGorskieDTO;
    }

    private ModelResponse<KolejkiGorskieDTO> mapToResponse(Page<KolejkiGorskieEntity> kolejkiGorskieEntities){
        ModelResponse<KolejkiGorskieDTO> kolejkiGorskieResponse = new ModelResponse<>();
        kolejkiGorskieResponse.setPageNo(kolejkiGorskieEntities.getNumber() + 1);
        kolejkiGorskieResponse.setPageSize(kolejkiGorskieEntities.getSize());
        kolejkiGorskieResponse.setTotalElements(kolejkiGorskieEntities.getTotalElements());
        kolejkiGorskieResponse.setTotalPages(kolejkiGorskieEntities.getTotalPages());
        kolejkiGorskieResponse.setLast(kolejkiGorskieEntities.isLast());
        return kolejkiGorskieResponse;
    }
}
