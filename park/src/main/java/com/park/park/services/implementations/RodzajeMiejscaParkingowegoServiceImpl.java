package com.park.park.services.implementations;

import com.park.park.dto.RodzajeMiejscaParkingowegoDTO;
import com.park.park.entities.RodzajeMiejscaParkingowegoEntity;
import com.park.park.repositories.MiejscaParkingoweRepository;
import com.park.park.repositories.RodzajeMiejscaParkingowegoRepository;
import com.park.park.responses.ModelResponse;
import com.park.park.services.RodzajeMiejscaParkingowegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RodzajeMiejscaParkingowegoServiceImpl implements RodzajeMiejscaParkingowegoService {
    private final RodzajeMiejscaParkingowegoRepository rodzajeMiejscaParkingowegoRepository;
    private final MiejscaParkingoweRepository miejscaParkingoweRepository;
    @Autowired
    public RodzajeMiejscaParkingowegoServiceImpl(RodzajeMiejscaParkingowegoRepository rodzajeMiejscaParkingowegoRepository, MiejscaParkingoweRepository miejscaParkingoweRepository) {
        this.rodzajeMiejscaParkingowegoRepository = rodzajeMiejscaParkingowegoRepository;
        this.miejscaParkingoweRepository = miejscaParkingoweRepository;
    }

    @Override
    public ModelResponse<RodzajeMiejscaParkingowegoDTO> getAllRodzajeMiejscaParkingowego(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<RodzajeMiejscaParkingowegoEntity> rodzajeMiejscaParkingowegoEntities =
                rodzajeMiejscaParkingowegoRepository.findAll(pageable);
        List<RodzajeMiejscaParkingowegoEntity> rodzajeMiejscaParkingowegoEntityList =
                rodzajeMiejscaParkingowegoEntities.getContent();
        List<RodzajeMiejscaParkingowegoDTO> content =
                rodzajeMiejscaParkingowegoEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<RodzajeMiejscaParkingowegoDTO> rodzajeMiejscaParkingowegoResponse =
                mapToResponse(rodzajeMiejscaParkingowegoEntities);
        rodzajeMiejscaParkingowegoResponse.setContent(content);

        return rodzajeMiejscaParkingowegoResponse;
    }

    @Override
    public RodzajeMiejscaParkingowegoDTO getRodzajeMiejscaParkingowegoById(long idRodzajuMiejscaParkingowego) {
        RodzajeMiejscaParkingowegoEntity rodzajeMiejscaParkingowegoEntity =
                rodzajeMiejscaParkingowegoRepository.findById(idRodzajuMiejscaParkingowego)
                        .orElseThrow(() -> new RuntimeException("Nie ma rodzaju miejsca parkingowego o zadanym id"));

        return mapToDTO(rodzajeMiejscaParkingowegoEntity);
    }

    @Override
    public RodzajeMiejscaParkingowegoDTO createRodzajeMiejscaParkingowego(
            RodzajeMiejscaParkingowegoDTO rodzajeMiejscaParkingowegoDTO) {
        RodzajeMiejscaParkingowegoEntity rodzajeMiejscaParkingowegoEntity =
                rodzajeMiejscaParkingowegoRepository.save(mapToEntity(rodzajeMiejscaParkingowegoDTO));

        return mapToDTO(rodzajeMiejscaParkingowegoEntity);
    }

    @Override
    public RodzajeMiejscaParkingowegoDTO updateRodzajeMiejscaParkingowego(
            RodzajeMiejscaParkingowegoDTO rodzajeMiejscaParkingowegoDTO, long idRodzajuMiejscaParkingowego) {
        RodzajeMiejscaParkingowegoEntity rodzajeMiejscaParkingowegoEntity =
                rodzajeMiejscaParkingowegoRepository.findById(idRodzajuMiejscaParkingowego)
                        .orElseThrow(() -> new RuntimeException("Nie ma rodzaju miejsca parkingowego o zadanym id"));

        if (rodzajeMiejscaParkingowegoDTO.getStandard() != null)
            rodzajeMiejscaParkingowegoEntity.setStandard(rodzajeMiejscaParkingowegoDTO.getStandard());
        if (rodzajeMiejscaParkingowegoDTO.getStawkaGodzinowa() != 0)
            rodzajeMiejscaParkingowegoEntity.setStawkaGodzinowa(rodzajeMiejscaParkingowegoDTO.getStawkaGodzinowa());
        if (rodzajeMiejscaParkingowegoDTO.getOpisMiejscaParkingowego() != null)
            rodzajeMiejscaParkingowegoEntity.setOpisMiejscaParkingowego(
                    rodzajeMiejscaParkingowegoDTO.getOpisMiejscaParkingowego());

        RodzajeMiejscaParkingowegoEntity finalRodzajeMiejscaParkingowegoEntity =
                rodzajeMiejscaParkingowegoRepository.save(rodzajeMiejscaParkingowegoEntity);

        return mapToDTO(finalRodzajeMiejscaParkingowegoEntity);
    }

    @Override
    public void deleteRodzajeMiejscaParkingowego(long idRodzajuMiejscaParkingowego) {
        RodzajeMiejscaParkingowegoEntity rodzajeMiejscaParkingowegoEntity =
                rodzajeMiejscaParkingowegoRepository.findById(idRodzajuMiejscaParkingowego)
                        .orElseThrow(() -> new RuntimeException("Nie ma rodzaju miejsca parkingowego o zadanym id"));

        miejscaParkingoweRepository.deleteAllByIdRodzajuMiejscaParkingowego(idRodzajuMiejscaParkingowego);

        rodzajeMiejscaParkingowegoRepository.delete(rodzajeMiejscaParkingowegoEntity);
    }

    private RodzajeMiejscaParkingowegoDTO mapToDTO(RodzajeMiejscaParkingowegoEntity rodzajeMiejscaParkingowegoEntity){
        RodzajeMiejscaParkingowegoDTO rodzajeMiejscaParkingowegoDTO = new RodzajeMiejscaParkingowegoDTO();

        rodzajeMiejscaParkingowegoDTO.setIdRodzajuMiejscaParkingowego(
                rodzajeMiejscaParkingowegoEntity.getIdRodzajuMiejscaParkingowego());
        rodzajeMiejscaParkingowegoDTO.setStandard(
                rodzajeMiejscaParkingowegoEntity.getStandard());
        rodzajeMiejscaParkingowegoDTO.setStawkaGodzinowa(
                rodzajeMiejscaParkingowegoEntity.getStawkaGodzinowa());
        rodzajeMiejscaParkingowegoDTO.setOpisMiejscaParkingowego(
                rodzajeMiejscaParkingowegoEntity.getOpisMiejscaParkingowego());

        return rodzajeMiejscaParkingowegoDTO;
    }

    private RodzajeMiejscaParkingowegoEntity mapToEntity(RodzajeMiejscaParkingowegoDTO rodzajeMiejscaParkingowegoDTO){
        RodzajeMiejscaParkingowegoEntity rodzajeMiejscaParkingowegoEntity = new RodzajeMiejscaParkingowegoEntity();

        rodzajeMiejscaParkingowegoEntity.setStandard(
                rodzajeMiejscaParkingowegoDTO.getStandard());
        rodzajeMiejscaParkingowegoEntity.setStawkaGodzinowa(
                rodzajeMiejscaParkingowegoDTO.getStawkaGodzinowa());
        rodzajeMiejscaParkingowegoEntity.setOpisMiejscaParkingowego(
                rodzajeMiejscaParkingowegoDTO.getOpisMiejscaParkingowego());

        return rodzajeMiejscaParkingowegoEntity;
    }

    private ModelResponse<RodzajeMiejscaParkingowegoDTO> mapToResponse(
            Page<RodzajeMiejscaParkingowegoEntity> rodzajeMiejscaParkingowegoEntities){
        ModelResponse<RodzajeMiejscaParkingowegoDTO> rodzajeMiejscaParkingowegoResponse = new ModelResponse<>();

        rodzajeMiejscaParkingowegoResponse.setPageNo(rodzajeMiejscaParkingowegoEntities.getNumber() + 1);
        rodzajeMiejscaParkingowegoResponse.setPageSize(rodzajeMiejscaParkingowegoEntities.getSize());
        rodzajeMiejscaParkingowegoResponse.setTotalPages(rodzajeMiejscaParkingowegoEntities.getTotalPages());
        rodzajeMiejscaParkingowegoResponse.setTotalElements(rodzajeMiejscaParkingowegoEntities.getTotalElements());
        rodzajeMiejscaParkingowegoResponse.setLast(rodzajeMiejscaParkingowegoEntities.isLast());

        return rodzajeMiejscaParkingowegoResponse;
    }
}
