package com.park.park.services;

import com.park.park.dto.AtrakcjeDTO;
import com.park.park.entities.AtrakcjeEntity;
import com.park.park.repositories.AtrakcjeRepository;
import com.park.park.responses.AtrakcjeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AtrakcjeServiceImpl implements AtrakcjeService{
    private final AtrakcjeRepository atrakcjeRepository;

    @Autowired
    public AtrakcjeServiceImpl(AtrakcjeRepository atrakcjeRepository) {
        this.atrakcjeRepository = atrakcjeRepository;
    }

    @Override
    public AtrakcjeResponse getAllAtrakcje(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<AtrakcjeEntity> atrakcjeEntities = atrakcjeRepository.findAll(pageable);
        List<AtrakcjeEntity> atrakcjeEntityList = atrakcjeEntities.getContent();
        List<AtrakcjeDTO> content = atrakcjeEntityList.stream().map(this::mapToDTO).toList();
        AtrakcjeResponse atrakcjeResponse = mapToResponse(atrakcjeEntities);
        atrakcjeResponse.setContent(content);

        return atrakcjeResponse;
    }

    @Override
    public AtrakcjeDTO createAtrakcje(AtrakcjeDTO atrakcjeDTO) {
        AtrakcjeEntity atrakcjeEntity = mapToEntity(atrakcjeDTO);
        AtrakcjeEntity finalAtrakcjeEntity = atrakcjeRepository.save(atrakcjeEntity);
        System.out.println("yes");
        return mapToDTO(finalAtrakcjeEntity);
    }

    @Override
    public AtrakcjeDTO updateAtrakcje(AtrakcjeDTO atrakcjeDTO, long idAtrakcji) {
        return null;
    }

    @Override
    public void deleteAtrakcje(long idAtrakcji) {

    }

    private AtrakcjeEntity mapToEntity(AtrakcjeDTO atrakcjeDTO){
        AtrakcjeEntity atrakcjeEntity = new AtrakcjeEntity();
        atrakcjeEntity.setNazwaAtrakcji(atrakcjeDTO.getNazwaAtrakcji());
        atrakcjeEntity.setTypAtrakcji(atrakcjeDTO.getTypAtrakcji());
        atrakcjeEntity.setOpisAtrakcji(atrakcjeDTO.getOpisAtrakcji());
        atrakcjeEntity.setIdParkuRozrywki(atrakcjeDTO.getIdParkuRozrywki());
        return atrakcjeEntity;
    }

    private AtrakcjeDTO mapToDTO(AtrakcjeEntity atrakcjeEntity){
        AtrakcjeDTO atrakcjeDTO = new AtrakcjeDTO();
        atrakcjeDTO.setIdAtrakcji(atrakcjeEntity.getIdAtrakcji());
        atrakcjeDTO.setNazwaAtrakcji(atrakcjeEntity.getNazwaAtrakcji());
        atrakcjeDTO.setTypAtrakcji(atrakcjeEntity.getTypAtrakcji());
        atrakcjeDTO.setOpisAtrakcji(atrakcjeEntity.getOpisAtrakcji());
        atrakcjeDTO.setIdParkuRozrywki(atrakcjeEntity.getIdParkuRozrywki());
        return atrakcjeDTO;
    }

    private AtrakcjeResponse mapToResponse(Page<AtrakcjeEntity> reviews){
        AtrakcjeResponse atrakcjeResponse = new AtrakcjeResponse();
        atrakcjeResponse.setPageNo(reviews.getNumber() + 1);
        atrakcjeResponse.setPageSize(reviews.getSize());
        atrakcjeResponse.setTotalElements(reviews.getTotalElements());
        atrakcjeResponse.setTotalPages(reviews.getTotalPages());
        atrakcjeResponse.setLast(reviews.isLast());
        return atrakcjeResponse;
    }
}
