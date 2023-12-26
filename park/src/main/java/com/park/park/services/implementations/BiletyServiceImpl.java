package com.park.park.services.implementations;

import com.park.park.dto.BiletyDTO;
import com.park.park.entities.BiletyEntity;
import com.park.park.entities.TypyBiletowEntity;
import com.park.park.repositories.BiletyRepository;
import com.park.park.repositories.TypyBiletowRepository;
import com.park.park.responses.ModelResponse;
import com.park.park.services.BiletyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class BiletyServiceImpl implements BiletyService {
    private final BiletyRepository biletyRepository;
    private final TypyBiletowRepository typyBiletowRepository;
    @Autowired
    public BiletyServiceImpl(BiletyRepository biletyRepository, TypyBiletowRepository typyBiletowRepository) {
        this.biletyRepository = biletyRepository;
        this.typyBiletowRepository = typyBiletowRepository;
    }

    @Override
    public ModelResponse<BiletyDTO> getAllBilety(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<BiletyEntity> biletyEntities = biletyRepository.findAll(pageable);
        List<BiletyEntity> biletyEntityList = biletyEntities.getContent();
        List<BiletyDTO> content = biletyEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<BiletyDTO> biletyResponse = mapToResponse(biletyEntities);
        biletyResponse.setContent(content);

        return biletyResponse;
    }

    @Override
    public ModelResponse<BiletyDTO> getAllBiletyByKlient(long idKlienta, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<BiletyEntity> biletyEntities = biletyRepository.findAllByIdKlienta(idKlienta, pageable);
        List<BiletyEntity> biletyEntityList = biletyEntities.getContent();
        List<BiletyDTO> content = biletyEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<BiletyDTO> biletyResponse = mapToResponse(biletyEntities);
        biletyResponse.setContent(content);

        return biletyResponse;
    }

    @Override
    public BiletyDTO getBiletyById(long idBiletu) {
        BiletyEntity biletyEntity = biletyRepository.findById(idBiletu)
                .orElseThrow(() -> new RuntimeException("Nie ma biletu o zadanym id"));

        return mapToDTO(biletyEntity);
    }

    @Override
    public BiletyDTO createBilety(BiletyDTO biletyDTO, long idKlienta) {
        typyBiletowRepository.findById(biletyDTO.getIdTypuBiletu())
                .orElseThrow(() -> new RuntimeException("Nie ma typu biletu o zadanym id"));

        BiletyEntity biletyEntity = mapToEntity(biletyDTO, idKlienta);
        BiletyEntity finalBiletyEntity = biletyRepository.save(biletyEntity);

        return mapToDTO(finalBiletyEntity);
    }

    @Override
    public BiletyDTO updateBilety(BiletyDTO biletyDTO, long idBiletu) {
        BiletyEntity biletyEntity = biletyRepository.findById(idBiletu)
                .orElseThrow(() -> new RuntimeException("Nie ma biletu o zadanym id"));

        if (biletyDTO.getDataWaznosci() != null) biletyEntity.setDataWaznosci(Date.valueOf(biletyDTO.getDataWaznosci()));
        if (biletyDTO.getIdKlienta() != -1) biletyEntity.setIdKlienta(biletyDTO.getIdKlienta());
        if (biletyDTO.getIdTypuBiletu() != -1) {
            typyBiletowRepository.findById(biletyDTO.getIdTypuBiletu())
                    .orElseThrow(() -> new RuntimeException("Nie ma typu biletu o zadanym id"));
            biletyEntity.setIdTypuBiletu(biletyDTO.getIdTypuBiletu());
        }

        BiletyEntity updatedBiletyEntity = biletyRepository.save(biletyEntity);

        return mapToDTO(updatedBiletyEntity);
    }

    @Override
    public void deleteBilety(long idBiletu) {
        BiletyEntity biletyEntity = biletyRepository.findById(idBiletu)
                .orElseThrow(() -> new RuntimeException("Nie ma biletu o zadanym id"));
        biletyRepository.delete(biletyEntity);
    }

    private BiletyDTO mapToDTO(BiletyEntity biletyEntity) {
        BiletyDTO biletyDTO = new BiletyDTO();
        biletyDTO.setIdBiletu(biletyEntity.getIdBiletu());
        biletyDTO.setDataWaznosci(String.valueOf(biletyEntity.getDataWaznosci()));
        biletyDTO.setDataZakupu(String.valueOf(biletyEntity.getDataZakupu()));
        biletyDTO.setIdKlienta(biletyEntity.getIdKlienta());
        biletyDTO.setIdTypuBiletu(biletyEntity.getIdTypuBiletu());
        biletyDTO.setIdParkuRozrywki(biletyEntity.getIdParkuRozrywki());

        TypyBiletowEntity typyBiletowEntity = typyBiletowRepository.findById(biletyEntity.getIdTypuBiletu())
                .orElseThrow(() -> new RuntimeException("Nie ma typu biletu o zadanym id"));

        biletyDTO.setStandard(typyBiletowEntity.getStandard());
        biletyDTO.setCena(typyBiletowEntity.getCena());
        biletyDTO.setUlga(typyBiletowEntity.getUlga());
        biletyDTO.setOpisTypuBiletu(typyBiletowEntity.getOpisTypuBiletu());

        return biletyDTO;
    }

    private BiletyEntity mapToEntity(BiletyDTO biletyDTO, long idKlienta) {
        BiletyEntity biletyEntity = new BiletyEntity();

        LocalDate now = LocalDate.now();
        LocalDate plusDays = now.plusDays(30);

        biletyEntity.setDataWaznosci(Date.valueOf(plusDays));
        biletyEntity.setDataZakupu(Date.valueOf(now));
        biletyEntity.setIdKlienta(idKlienta);
        biletyEntity.setIdTypuBiletu(biletyDTO.getIdTypuBiletu());
        biletyEntity.setIdParkuRozrywki(1);
        return biletyEntity;
    }

    private ModelResponse<BiletyDTO> mapToResponse(Page<BiletyEntity> biletyEntities) {
        ModelResponse<BiletyDTO> biletyResponse = new ModelResponse<>();
        biletyResponse.setTotalPages(biletyEntities.getTotalPages());
        biletyResponse.setTotalElements(biletyEntities.getTotalElements());
        biletyResponse.setPageNo(biletyEntities.getNumber() + 1);
        biletyResponse.setPageSize(biletyEntities.getSize());
        biletyResponse.setLast(biletyEntities.isLast());
        return biletyResponse;
    }
}
