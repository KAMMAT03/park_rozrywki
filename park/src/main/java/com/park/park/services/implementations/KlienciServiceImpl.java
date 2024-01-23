package com.park.park.services.implementations;

import com.park.park.dto.KlienciDTO;
import com.park.park.entities.KlienciEntity;
import com.park.park.repositories.BiletyRepository;
import com.park.park.repositories.KlienciRepository;
import com.park.park.repositories.MiejscaParkingoweRepository;
import com.park.park.responses.ModelResponse;
import com.park.park.services.KlienciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlienciServiceImpl implements KlienciService {
    private final KlienciRepository klienciRepository;
    private final BiletyRepository biletyRepository;
    private final MiejscaParkingoweRepository miejscaParkingoweRepository;
    @Autowired
    public KlienciServiceImpl(KlienciRepository klienciRepository, BiletyRepository biletyRepository, MiejscaParkingoweRepository miejscaParkingoweRepository) {
        this.klienciRepository = klienciRepository;
        this.biletyRepository = biletyRepository;
        this.miejscaParkingoweRepository = miejscaParkingoweRepository;
    }

    @Override
    public ModelResponse<KlienciDTO> getAllKlienci(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<KlienciEntity> klienciEntities = klienciRepository.findAll(pageable);
        List<KlienciEntity> klienciEntityList = klienciEntities.getContent();
        List<KlienciDTO> content = klienciEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<KlienciDTO> klienciResponse = mapToResponse(klienciEntities);
        klienciResponse.setContent(content);

        return klienciResponse;
    }

    @Override
    public KlienciDTO getKlienciById(long idKlienta) {
        KlienciEntity klienciEntity = klienciRepository.findById(idKlienta)
                .orElseThrow(() -> new RuntimeException("Nie ma klienta o zadanym id"));
        return mapToDTO(klienciEntity);
    }

    @Override
    public KlienciDTO createKlienci(KlienciDTO klienciDTO) {
        KlienciEntity klienciEntity = mapToEntity(klienciDTO);
        KlienciEntity finalKlienciEntity = klienciRepository.save(klienciEntity);

        return mapToDTO(finalKlienciEntity);
    }

    @Override
    public KlienciDTO updateKlienci(KlienciDTO klienciDTO, long idKlienta) {
        KlienciEntity klienciEntity = klienciRepository.findById(idKlienta)
                .orElseThrow(() -> new RuntimeException("Nie ma klienta o zadanym id"));

        if (klienciDTO.getImie() != null) klienciEntity.setImie(klienciDTO.getImie());
        if (klienciDTO.getNazwisko() != null) klienciEntity.setNazwisko(klienciDTO.getNazwisko());
        if (klienciDTO.getDataUrodzenia() != null)
            klienciEntity.setDataUrodzenia(java.sql.Date.valueOf(klienciDTO.getDataUrodzenia()));

        KlienciEntity updatedKlienci = klienciRepository.save(klienciEntity);
        return mapToDTO(updatedKlienci);
    }

    @Override
    public void deleteKlienci(long idKlienta) {
        KlienciEntity klienciEntity = klienciRepository.findById(idKlienta)
                .orElseThrow(() -> new RuntimeException("Nie ma klienta o zadanym id"));

        biletyRepository.deleteAllByIdKlienta(idKlienta);
        miejscaParkingoweRepository.deleteAllByIdKlienta(idKlienta);

        klienciRepository.delete(klienciEntity);
    }

    private KlienciDTO mapToDTO(KlienciEntity klienciEntity) {
        KlienciDTO klienciDTO = new KlienciDTO();
        klienciDTO.setIdKlienta(klienciEntity.getIdKlienta());
        klienciDTO.setImie(klienciEntity.getImie());
        klienciDTO.setNazwisko(klienciEntity.getNazwisko());
        klienciDTO.setDataUrodzenia(String.valueOf(klienciEntity.getDataUrodzenia()));
        return klienciDTO;
    }

    private KlienciEntity mapToEntity(KlienciDTO klienciDTO) {
        KlienciEntity klienciEntity = new KlienciEntity();
        klienciEntity.setImie(klienciDTO.getImie());
        klienciEntity.setNazwisko(klienciDTO.getNazwisko());
        klienciEntity.setDataUrodzenia(java.sql.Date.valueOf(klienciDTO.getDataUrodzenia()));
        return klienciEntity;
    }

    private ModelResponse<KlienciDTO> mapToResponse(Page<KlienciEntity> klienciEntities) {
        ModelResponse<KlienciDTO> klienciResponse = new ModelResponse<>();
        klienciResponse.setTotalPages(klienciEntities.getTotalPages());
        klienciResponse.setTotalElements(klienciEntities.getTotalElements());
        klienciResponse.setPageNo(klienciEntities.getNumber() + 1);
        klienciResponse.setPageSize(klienciEntities.getSize());
        klienciResponse.setLast(klienciEntities.isLast());
        return klienciResponse;
    }
}
