package com.park.park.controllers;

import com.park.park.dto.KolejkiGorskieDTO;
import com.park.park.responses.DeleteResponse;
import com.park.park.responses.ModelResponse;
import com.park.park.services.KolejkiGorskieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/kolejki")
public class KolejkiGorskieController {
    KolejkiGorskieService kolejkiGorskieService;
    @Autowired
    public KolejkiGorskieController(KolejkiGorskieService kolejkiGorskieService) {
        this.kolejkiGorskieService = kolejkiGorskieService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<KolejkiGorskieDTO> createKolejkiGorskie(@RequestBody KolejkiGorskieDTO kolejkiGorskieDTO){
        return new ResponseEntity<>(kolejkiGorskieService.createKolejkiGorskie(kolejkiGorskieDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{idAtrakcji}")
    public ResponseEntity<KolejkiGorskieDTO> getKolejkiGorskieById(
            @PathVariable(value = "idAtrakcji") long idAtrakcji){
        return new ResponseEntity<>(kolejkiGorskieService.getKolejkiGorskieById(idAtrakcji), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ModelResponse<KolejkiGorskieDTO>> getAllKolejkiGorskie(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(kolejkiGorskieService.getAllKolejkiGorskie(pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/update/{idAtrakcji}")
    public ResponseEntity<KolejkiGorskieDTO> updateKolejkiGorskie(@PathVariable(value = "idAtrakcji") long idAtrakcji,
                                                      @RequestBody KolejkiGorskieDTO kolejkiGorskieDTO){
        return new ResponseEntity<>(kolejkiGorskieService.
                updateKolejkiGorskie(kolejkiGorskieDTO, idAtrakcji), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idAtrakcji}")
    public ResponseEntity<DeleteResponse> deleteKolejkiGorskie(@PathVariable(value = "idAtrakcji") long idAtrakcji){
        kolejkiGorskieService.deleteKolejkiGorskie(idAtrakcji);
        return new ResponseEntity<>(new DeleteResponse("Kolejka górska została usunięta", idAtrakcji), HttpStatus.OK);
    }
}
