package com.park.park.services.implementations;

import com.park.park.dto.MiejscaParkingoweDTO;
import com.park.park.entities.MiejscaParkingoweEntity;
import com.park.park.repositories.MiejscaParkingoweRepository;
import com.park.park.repositories.RodzajeMiejscaParkingowegoRepository;
import com.park.park.responses.ModelResponse;
import com.park.park.services.MiejscaParkingoweService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiejscaParkingoweServiceImpl implements MiejscaParkingoweService {
    private final MiejscaParkingoweRepository miejscaParkingoweRepository;
    private final RodzajeMiejscaParkingowegoRepository rodzajeMiejscaParkingowegoRepository;
    @Autowired
    public MiejscaParkingoweServiceImpl(MiejscaParkingoweRepository miejscaParkingoweRepository,
                                        RodzajeMiejscaParkingowegoRepository rodzajeMiejscaParkingowegoRepository) {
        this.miejscaParkingoweRepository = miejscaParkingoweRepository;
        this.rodzajeMiejscaParkingowegoRepository = rodzajeMiejscaParkingowegoRepository;
    }

    @Override
    public ModelResponse<MiejscaParkingoweDTO> getAllMiejscaParkingowe(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<MiejscaParkingoweEntity> miejscaParkingoweEntities = miejscaParkingoweRepository.findAll(pageable);
        List<MiejscaParkingoweEntity> miejscaParkingoweEntityList = miejscaParkingoweEntities.getContent();
        List<MiejscaParkingoweDTO> content = miejscaParkingoweEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<MiejscaParkingoweDTO> miejscaParkingoweResponse = mapToResponse(miejscaParkingoweEntities);
        miejscaParkingoweResponse.setContent(content);

        return miejscaParkingoweResponse;
    }

    @Override
    public ModelResponse<MiejscaParkingoweDTO> getAllMiejscaParkingoweByRodzaj(long idRodzaju, int pageNo, int pageSize) {
        rodzajeMiejscaParkingowegoRepository.findById(idRodzaju)
                .orElseThrow(() -> new RuntimeException("Nie ma rodzaju miejsca parkingowego o zadanym id"));

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<MiejscaParkingoweEntity> miejscaParkingoweEntities =
                miejscaParkingoweRepository.findAllByIdRodzajuMiejscaParkingowego(idRodzaju, pageable);
        List<MiejscaParkingoweEntity> miejscaParkingoweEntityList = miejscaParkingoweEntities.getContent();
        List<MiejscaParkingoweDTO> content = miejscaParkingoweEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<MiejscaParkingoweDTO> miejscaParkingoweResponse = mapToResponse(miejscaParkingoweEntities);
        miejscaParkingoweResponse.setContent(content);

        return miejscaParkingoweResponse;
    }

    @Override
    public MiejscaParkingoweDTO getMiejscaParkingoweById(long idMiejscaParkingowego) {
        MiejscaParkingoweEntity miejscaParkingoweEntity = miejscaParkingoweRepository.findById(idMiejscaParkingowego)
                .orElseThrow(() -> new RuntimeException("Nie ma miejsca parkingowego o zadanym id"));

        return mapToDTO(miejscaParkingoweEntity);
    }

    @Override
    public MiejscaParkingoweDTO createMiejscaParkingowe(MiejscaParkingoweDTO miejscaParkingoweDTO) {
        rodzajeMiejscaParkingowegoRepository.findById(miejscaParkingoweDTO.getIdRodzajuMiejscaParkingowego())
                .orElseThrow(() -> new RuntimeException("Nie ma rodzaju miejsca parkingowego o zadanym id"));

        MiejscaParkingoweEntity miejscaParkingoweEntity = mapToEntity(miejscaParkingoweDTO);
        MiejscaParkingoweEntity finalMiejscaParkingoweEntity =
                miejscaParkingoweRepository.save(miejscaParkingoweEntity);

        return mapToDTO(finalMiejscaParkingoweEntity);
    }

    @Override
    public MiejscaParkingoweDTO updateMiejscaParkingowe(
            MiejscaParkingoweDTO miejscaParkingoweDTO, long idMiejscaParkingowego) {
        MiejscaParkingoweEntity miejscaParkingoweEntity = miejscaParkingoweRepository.findById(idMiejscaParkingowego)
                .orElseThrow(() -> new RuntimeException("Nie ma miejsca parkingowego o zadanym id"));

        if (miejscaParkingoweDTO.getSektor() != null)
            miejscaParkingoweEntity.setSektor(miejscaParkingoweDTO.getSektor());
        if (miejscaParkingoweDTO.getMiasto() != null)
            miejscaParkingoweEntity.setMiasto(miejscaParkingoweDTO.getMiasto());
        if (miejscaParkingoweDTO.getUlica() != null)
            miejscaParkingoweEntity.setUlica(miejscaParkingoweDTO.getUlica());
        if (miejscaParkingoweDTO.getKodPocztowy() != null)
            miejscaParkingoweEntity.setKodPocztowy(miejscaParkingoweDTO.getKodPocztowy());
        if (miejscaParkingoweDTO.getNrPosesji() != null)
            miejscaParkingoweEntity.setNrPosesji(miejscaParkingoweDTO.getNrPosesji());
        if (miejscaParkingoweDTO.getNazwaParkingu() != null)
            miejscaParkingoweEntity.setNazwaParkingu(miejscaParkingoweDTO.getNazwaParkingu());
        if (miejscaParkingoweDTO.getIdKlienta() != -1)
            miejscaParkingoweEntity.setIdKlienta(miejscaParkingoweDTO.getIdKlienta());
        if (miejscaParkingoweDTO.getIdRodzajuMiejscaParkingowego() != -1) {
            rodzajeMiejscaParkingowegoRepository.findById(miejscaParkingoweDTO.getIdRodzajuMiejscaParkingowego())
                    .orElseThrow(() -> new RuntimeException("Nie ma rodzaju miejsca parkingowego o zadanym id"));
            miejscaParkingoweEntity.setIdRodzajuMiejscaParkingowego
                    (miejscaParkingoweDTO.getIdRodzajuMiejscaParkingowego());
        }

        MiejscaParkingoweEntity updatedMiejscaParkingoweEntity =
                miejscaParkingoweRepository.save(miejscaParkingoweEntity);

        return mapToDTO(updatedMiejscaParkingoweEntity);
    }

    @Override
    public void deleteMiejscaParkingowe(long idMiejscaParkingowego) {
        miejscaParkingoweRepository.findById(idMiejscaParkingowego)
                .orElseThrow(() -> new RuntimeException("Nie ma miejsca parkingowego o zadanym id"));

        miejscaParkingoweRepository.deleteById(idMiejscaParkingowego);
    }

    private MiejscaParkingoweDTO mapToDTO(MiejscaParkingoweEntity miejscaParkingoweEntity){
        MiejscaParkingoweDTO miejscaParkingoweDTO = new MiejscaParkingoweDTO();

        miejscaParkingoweDTO.setIdMiejscaParkingowego(miejscaParkingoweEntity.getIdMiejscaParkingowego());
        miejscaParkingoweDTO.setSektor(miejscaParkingoweEntity.getSektor());
        miejscaParkingoweDTO.setMiasto(miejscaParkingoweEntity.getMiasto());
        miejscaParkingoweDTO.setUlica(miejscaParkingoweEntity.getUlica());
        miejscaParkingoweDTO.setKodPocztowy(miejscaParkingoweEntity.getKodPocztowy());
        miejscaParkingoweDTO.setNrPosesji(miejscaParkingoweEntity.getNrPosesji());
        miejscaParkingoweDTO.setNazwaParkingu(miejscaParkingoweEntity.getNazwaParkingu());
        miejscaParkingoweDTO.setIdKlienta(miejscaParkingoweEntity.getIdKlienta());
        miejscaParkingoweDTO.setIdParkuRozrywki(miejscaParkingoweEntity.getIdParkuRozrywki());
        miejscaParkingoweDTO.setIdRodzajuMiejscaParkingowego(miejscaParkingoweEntity.getIdRodzajuMiejscaParkingowego());

        return miejscaParkingoweDTO;
    }

    private MiejscaParkingoweEntity mapToEntity(MiejscaParkingoweDTO miejscaParkingoweDTO){
        MiejscaParkingoweEntity miejscaParkingoweEntity = new MiejscaParkingoweEntity();

        miejscaParkingoweEntity.setSektor(miejscaParkingoweDTO.getSektor());
        miejscaParkingoweEntity.setMiasto(miejscaParkingoweDTO.getMiasto());
        miejscaParkingoweEntity.setUlica(miejscaParkingoweDTO.getUlica());
        miejscaParkingoweEntity.setKodPocztowy(miejscaParkingoweDTO.getKodPocztowy());
        miejscaParkingoweEntity.setNrPosesji(miejscaParkingoweDTO.getNrPosesji());
        miejscaParkingoweEntity.setNazwaParkingu(miejscaParkingoweDTO.getNazwaParkingu());
        miejscaParkingoweEntity.setIdKlienta(miejscaParkingoweDTO.getIdKlienta());
        miejscaParkingoweEntity.setIdParkuRozrywki(1);
        miejscaParkingoweEntity.setIdRodzajuMiejscaParkingowego(miejscaParkingoweDTO.getIdRodzajuMiejscaParkingowego());

        return miejscaParkingoweEntity;
    }

    private ModelResponse<MiejscaParkingoweDTO> mapToResponse(
            Page<MiejscaParkingoweEntity> miejscaParkingoweEntities){
        ModelResponse<MiejscaParkingoweDTO> miejscaParkingoweResponse = new ModelResponse<>();

        miejscaParkingoweResponse.setPageNo(miejscaParkingoweEntities.getNumber() + 1);
        miejscaParkingoweResponse.setPageSize(miejscaParkingoweEntities.getSize());
        miejscaParkingoweResponse.setTotalPages(miejscaParkingoweEntities.getTotalPages());
        miejscaParkingoweResponse.setTotalElements(miejscaParkingoweEntities.getTotalElements());
        miejscaParkingoweResponse.setLast(miejscaParkingoweEntities.isLast());

        return miejscaParkingoweResponse;
    }
}
