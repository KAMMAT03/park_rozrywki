package com.park.park.controllers;

import com.park.park.dto.MiejscaParkingoweDTO;
import com.park.park.responses.DeleteResponse;
import com.park.park.responses.ModelResponse;
import com.park.park.services.MiejscaParkingoweService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/parking")
public class MiejscaParkingoweController {
    private final MiejscaParkingoweService miejscaParkingoweService;
    @Autowired
    public MiejscaParkingoweController(MiejscaParkingoweService miejscaParkingoweService) {
        this.miejscaParkingoweService = miejscaParkingoweService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MiejscaParkingoweDTO> createMiejscaParkingowe(
            @RequestBody MiejscaParkingoweDTO miejscaParkingoweDTO){
        return new ResponseEntity<>(miejscaParkingoweService
                .createMiejscaParkingowe(miejscaParkingoweDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{idMiejscaParkingowego}")
    public ResponseEntity<MiejscaParkingoweDTO> getMiejscaParkingowe(
            @PathVariable(value = "idMiejscaParkingowego") long idMiejscaParkingowego){
        return new ResponseEntity<>(miejscaParkingoweService
                .getMiejscaParkingoweById(idMiejscaParkingowego), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ModelResponse<MiejscaParkingoweDTO>> getAllMiejscaParkingowe(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(miejscaParkingoweService
                .getAllMiejscaParkingowe(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/getall/{idRodzaju}")
    public ResponseEntity<ModelResponse<MiejscaParkingoweDTO>> getAllMiejscaParkingoweByRodzaj(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @PathVariable(value = "idRodzaju") long idRodzaju){
        return new ResponseEntity<>(miejscaParkingoweService
                .getAllMiejscaParkingoweByRodzaj(idRodzaju, pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/update/{idMiejscaParkingowego}")
    public ResponseEntity<MiejscaParkingoweDTO> updateMiejscaParkingowe(
            @PathVariable(value = "idMiejscaParkingowego") long idMiejscaParkingowego,
            @RequestBody MiejscaParkingoweDTO miejscaParkingoweDTO){
        return new ResponseEntity<>(miejscaParkingoweService
                .updateMiejscaParkingowe(miejscaParkingoweDTO, idMiejscaParkingowego), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idMiejscaParkingowego}")
    public ResponseEntity<DeleteResponse> deleteMiejscaParkingowe(
            @PathVariable(value = "idMiejscaParkingowego") long idMiejscaParkingowego){
        miejscaParkingoweService.deleteMiejscaParkingowe(idMiejscaParkingowego);
        return new ResponseEntity<>(new DeleteResponse("Miejsce parkingowe zostało usunięte", idMiejscaParkingowego), HttpStatus.OK);
    }
}
