package com.park.park.controllers;

import com.park.park.dto.GastronomieDTO;
import com.park.park.responses.DeleteResponse;
import com.park.park.responses.ModelResponse;
import com.park.park.services.GastronomieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/gastro")
public class GastronomieController {
    private final GastronomieService gastronomieService;

    @Autowired
    public GastronomieController(GastronomieService gastronomieService) {
        this.gastronomieService = gastronomieService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GastronomieDTO> createGastronomie(@RequestBody GastronomieDTO gastronomieDTO){
        return new ResponseEntity<>(gastronomieService.createGastronomie(gastronomieDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{idAtrakcji}")
    public ResponseEntity<GastronomieDTO> getGastronomie(
            @PathVariable(value = "idAtrakcji") long idAtrakcji){
        return new ResponseEntity<>(gastronomieService.getGastronomieById(idAtrakcji), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ModelResponse<GastronomieDTO>> getAllGastronomie(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(gastronomieService.getAllGastronomie(pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/update/{idAtrakcji}")
    public ResponseEntity<GastronomieDTO> updateGastronomie(@PathVariable(value = "idAtrakcji") long idAtrakcji,
                                                                  @RequestBody GastronomieDTO gastronomieDTO){
        return new ResponseEntity<>(gastronomieService.
                updateGastronomie(gastronomieDTO, idAtrakcji), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idAtrakcji}")
    public ResponseEntity<DeleteResponse> deleteGastronomie(@PathVariable(value = "idAtrakcji") long idAtrakcji){
        gastronomieService.deleteGastronomie(idAtrakcji);
        return new ResponseEntity<>(new DeleteResponse("Gastronomia została usunięta", idAtrakcji), HttpStatus.OK);
    }
}
