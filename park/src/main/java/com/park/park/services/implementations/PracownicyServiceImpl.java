package com.park.park.services.implementations;

import com.park.park.dto.PracownicyDTO;
import com.park.park.entities.PracownicyEntity;
import com.park.park.repositories.PracownicyRepository;
import com.park.park.repositories.StanowiskaRepository;
import com.park.park.responses.ModelResponse;
import com.park.park.services.PracownicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class PracownicyServiceImpl implements PracownicyService {
    private final PracownicyRepository pracownicyRepository;
    private final StanowiskaRepository stanowiskaRepository;
    @Autowired
    public PracownicyServiceImpl(PracownicyRepository pracownicyRepository, StanowiskaRepository stanowiskaRepository) {
        this.pracownicyRepository = pracownicyRepository;
        this.stanowiskaRepository = stanowiskaRepository;
    }

    @Override
    public ModelResponse<PracownicyDTO> getAllPracownicy(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<PracownicyEntity> pracownicyEntities = pracownicyRepository.findAll(pageable);
        List<PracownicyEntity> pracownicyEntityList = pracownicyEntities.getContent();
        List<PracownicyDTO> content = pracownicyEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<PracownicyDTO> pracownicyResponse = mapToResponse(pracownicyEntities);
        pracownicyResponse.setContent(content);

        return pracownicyResponse;
    }

    @Override
    public ModelResponse<PracownicyDTO> getAllPracownicyByStanowisko(long idStanowiska, int pageNo, int pageSize) {
        stanowiskaRepository.findById(idStanowiska)
                .orElseThrow(() -> new RuntimeException("Nie ma stanowiska o zadanym id"));

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<PracownicyEntity> pracownicyEntities = pracownicyRepository.findAllByIdStanowiska(idStanowiska, pageable);
        List<PracownicyEntity> pracownicyEntityList = pracownicyEntities.getContent();
        List<PracownicyDTO> content = pracownicyEntityList.stream().map(this::mapToDTO).toList();
        ModelResponse<PracownicyDTO> pracownicyResponse = mapToResponse(pracownicyEntities);
        pracownicyResponse.setContent(content);

        return pracownicyResponse;
    }

    @Override
    public PracownicyDTO getPracownicyById(long idPracownika) {
        PracownicyEntity pracownicyEntity = pracownicyRepository.findById(idPracownika)
                .orElseThrow(() -> new RuntimeException("Nie ma pracownika o zadanym id"));

        return mapToDTO(pracownicyEntity);
    }

    @Override
    public PracownicyDTO createPracownicy(PracownicyDTO pracownicyDTO) {
        stanowiskaRepository.findById(pracownicyDTO.getIdStanowiska())
                .orElseThrow(() -> new RuntimeException("Nie ma stanowiska o zadanym id"));

        PracownicyEntity pracownicyEntity = mapToEntity(pracownicyDTO);
        PracownicyEntity finalPracownicyEntity = pracownicyRepository.save(pracownicyEntity);

        return mapToDTO(finalPracownicyEntity);
    }

    @Override
    public PracownicyDTO updatePracownicy(PracownicyDTO pracownicyDTO, long idPracownika) {
        PracownicyEntity pracownicyEntity = pracownicyRepository.findById(idPracownika)
                .orElseThrow(() -> new RuntimeException("Nie ma pracownika o zadanym id"));

        if (pracownicyDTO.getNumerDokumentu() != null)
            pracownicyEntity.setNumerDokumentu(pracownicyDTO.getNumerDokumentu());
        if (pracownicyDTO.getImie() != null) pracownicyEntity.setImie(pracownicyDTO.getImie());
        if (pracownicyDTO.getNazwisko() != null)
            pracownicyEntity.setNazwisko(pracownicyDTO.getNazwisko());
        if (pracownicyDTO.getPesel() != null) pracownicyEntity.setPesel(pracownicyDTO.getPesel());
        if (pracownicyDTO.getDataUrodzenia() != null)
            pracownicyEntity.setDataUrodzenia(Date.valueOf(pracownicyDTO.getDataUrodzenia()));
        if (pracownicyDTO.getDataZatrudnienia() != null)
            pracownicyEntity.setDataZatrudnienia(Date.valueOf(pracownicyDTO.getDataZatrudnienia()));
        if (pracownicyDTO.getNrKonta() != null) pracownicyEntity.setNrKonta(pracownicyDTO.getNrKonta());
        if (pracownicyDTO.getNrTelefonu() != null)
            pracownicyEntity.setNrTelefonu(pracownicyDTO.getNrTelefonu());
        if (pracownicyDTO.getMiasto() != null) pracownicyEntity.setMiasto(pracownicyDTO.getMiasto());
        if (pracownicyDTO.getUlica() != null) pracownicyEntity.setUlica(pracownicyDTO.getUlica());
        if (pracownicyDTO.getKodPocztowy() != null)
            pracownicyEntity.setKodPocztowy(pracownicyDTO.getKodPocztowy());
        if (pracownicyDTO.getNrPosesji() != null)
            pracownicyEntity.setNrPosesji(pracownicyDTO.getNrPosesji());
        if (pracownicyDTO.getNrLokalu() != null) pracownicyEntity.setNrLokalu(pracownicyDTO.getNrLokalu());
        if (pracownicyDTO.getBonus() != -1) pracownicyEntity.setBonus(pracownicyDTO.getBonus());
        if (pracownicyDTO.getIdStanowiska() != -1)
            pracownicyEntity.setIdStanowiska(pracownicyDTO.getIdStanowiska());

        PracownicyEntity updatedPracownicy = pracownicyRepository.save(pracownicyEntity);

        return mapToDTO(updatedPracownicy);
    }

    @Override
    public void deletePracownicy(long idPracownika) {
        PracownicyEntity pracownicyEntity = pracownicyRepository.findById(idPracownika)
                .orElseThrow(() -> new RuntimeException("Nie ma pracownika o zadanym id"));

        pracownicyRepository.delete(pracownicyEntity);
    }

    private PracownicyDTO mapToDTO(PracownicyEntity pracownicyEntity) {
        PracownicyDTO pracownicyDTO = new PracownicyDTO();
        pracownicyDTO.setIdPracownika(pracownicyEntity.getIdPracownika());
        pracownicyDTO.setNumerDokumentu(pracownicyEntity.getNumerDokumentu());
        pracownicyDTO.setImie(pracownicyEntity.getImie());
        pracownicyDTO.setNazwisko(pracownicyEntity.getNazwisko());
        pracownicyDTO.setPesel(pracownicyEntity.getPesel());
        pracownicyDTO.setDataUrodzenia(String.valueOf(pracownicyEntity.getDataUrodzenia()));
        pracownicyDTO.setDataZatrudnienia(String.valueOf(pracownicyEntity.getDataZatrudnienia()));
        pracownicyDTO.setNrKonta(pracownicyEntity.getNrKonta());
        pracownicyDTO.setNrTelefonu(pracownicyEntity.getNrTelefonu());
        pracownicyDTO.setMiasto(pracownicyEntity.getMiasto());
        pracownicyDTO.setUlica(pracownicyEntity.getUlica());
        pracownicyDTO.setKodPocztowy(pracownicyEntity.getKodPocztowy());
        pracownicyDTO.setNrPosesji(pracownicyEntity.getNrPosesji());
        pracownicyDTO.setNrLokalu(pracownicyEntity.getNrLokalu());
        pracownicyDTO.setBonus(pracownicyEntity.getBonus());
        pracownicyDTO.setIdParkuRozrywki(pracownicyEntity.getIdParkuRozrywki());
        pracownicyDTO.setIdStanowiska(pracownicyEntity.getIdStanowiska());

        return pracownicyDTO;
    }

    private PracownicyEntity mapToEntity(PracownicyDTO pracownicyDTO) {
        PracownicyEntity pracownicyEntity = new PracownicyEntity();
        pracownicyEntity.setNumerDokumentu(pracownicyDTO.getNumerDokumentu());
        pracownicyEntity.setImie(pracownicyDTO.getImie());
        pracownicyEntity.setNazwisko(pracownicyDTO.getNazwisko());
        pracownicyEntity.setPesel(pracownicyDTO.getPesel());
        pracownicyEntity.setDataUrodzenia(Date.valueOf(pracownicyDTO.getDataUrodzenia()));
        pracownicyEntity.setDataZatrudnienia(Date.valueOf(LocalDate.now()));
        pracownicyEntity.setNrKonta(pracownicyDTO.getNrKonta());
        pracownicyEntity.setNrTelefonu(pracownicyDTO.getNrTelefonu());
        pracownicyEntity.setMiasto(pracownicyDTO.getMiasto());
        pracownicyEntity.setUlica(pracownicyDTO.getUlica());
        pracownicyEntity.setKodPocztowy(pracownicyDTO.getKodPocztowy());
        pracownicyEntity.setNrPosesji(pracownicyDTO.getNrPosesji());
        pracownicyEntity.setNrLokalu(pracownicyDTO.getNrLokalu());
        pracownicyEntity.setBonus(pracownicyDTO.getBonus());
        pracownicyEntity.setIdParkuRozrywki(1);
        pracownicyEntity.setIdStanowiska(pracownicyDTO.getIdStanowiska());

        return pracownicyEntity;
    }

    private ModelResponse<PracownicyDTO> mapToResponse(Page<PracownicyEntity> pracownicyEntities) {
        ModelResponse<PracownicyDTO> pracownicyResponse = new ModelResponse<>();
        pracownicyResponse.setTotalPages(pracownicyEntities.getTotalPages());
        pracownicyResponse.setTotalElements(pracownicyEntities.getTotalElements());
        pracownicyResponse.setPageNo(pracownicyEntities.getNumber() + 1);
        pracownicyResponse.setPageSize(pracownicyEntities.getSize());
        pracownicyResponse.setLast(pracownicyEntities.isLast());
        return pracownicyResponse;
    }
}
