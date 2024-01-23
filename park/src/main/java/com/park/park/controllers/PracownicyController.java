package com.park.park.controllers;

import com.park.park.dto.PracownicyDTO;
import com.park.park.responses.DeleteResponse;
import com.park.park.responses.ModelResponse;
import com.park.park.services.PracownicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/pracownicy")
public class PracownicyController {
    private final PracownicyService pracownicyService;
    @Autowired
    public PracownicyController(PracownicyService pracownicyService) {
        this.pracownicyService = pracownicyService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PracownicyDTO> createPracownicy(@RequestBody PracownicyDTO pracownicyDTO){
        return new ResponseEntity<>(pracownicyService.createPracownicy(pracownicyDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{idPracownika}")
    public ResponseEntity<PracownicyDTO> getPracownicy(
            @PathVariable(value = "idPracownika") long idPracownika){
        return new ResponseEntity<>(pracownicyService.getPracownicyById(idPracownika), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ModelResponse<PracownicyDTO>> getAllPracownicy(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(pracownicyService.getAllPracownicy(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/getall/{idStanowiska}")
    public ResponseEntity<ModelResponse<PracownicyDTO>> getAllPracownicyByStanowisko(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @PathVariable(value = "idStanowiska") long idStanowiska){
        return new ResponseEntity<>(pracownicyService
                .getAllPracownicyByStanowisko(idStanowiska, pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/update/{idPracownika}")
    public ResponseEntity<PracownicyDTO> updatePracownicy(@PathVariable(value = "idPracownika") long idPracownika,
                                                  @RequestBody PracownicyDTO pracownicyDTO){
        return new ResponseEntity<>(pracownicyService.updatePracownicy(pracownicyDTO, idPracownika), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idPracownika}")
    public ResponseEntity<DeleteResponse> deletePracownicy(@PathVariable(value = "idPracownika") long idPracownika){
        pracownicyService.deletePracownicy(idPracownika);
        return new ResponseEntity<>(new DeleteResponse("Pracownik został usunięty", idPracownika), HttpStatus.OK);
    }
}
