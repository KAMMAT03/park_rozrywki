package com.park.park.controllers;

import com.park.park.dto.BiletyDTO;
import com.park.park.responses.ModelResponse;
import com.park.park.services.BiletyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/bilety")
public class BiletyController {
    private final BiletyService biletyService;
    @Autowired
    public BiletyController(BiletyService biletyService) {
        this.biletyService = biletyService;
    }

    @PostMapping("/create/{idKlienta}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BiletyDTO> createBilety(@PathVariable(value = "idKlienta") long idKlienta,
                                                  @RequestBody BiletyDTO biletyDTO){
        return new ResponseEntity<>(biletyService.createBilety(biletyDTO, idKlienta), HttpStatus.CREATED);
    }

    @GetMapping("/get/{idBiletu}")
    public ResponseEntity<BiletyDTO> getBilety(
            @PathVariable(value = "idBiletu") long idBiletu){
        return new ResponseEntity<>(biletyService.getBiletyById(idBiletu), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ModelResponse<BiletyDTO>> getAllBilety(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(biletyService.getAllBilety(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/getall/{idKlienta}")
    public ResponseEntity<ModelResponse<BiletyDTO>> getAllBiletyByKlient(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @PathVariable(value = "idKlienta") long idKlienta){
        return new ResponseEntity<>(biletyService.getAllBiletyByKlient(idKlienta, pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/update/{idBiletu}")
    public ResponseEntity<BiletyDTO> updateBilety(@PathVariable(value = "idBiletu") long idBiletu,
                                                            @RequestBody BiletyDTO biletyDTO){
        return new ResponseEntity<>(biletyService.updateBilety(biletyDTO, idBiletu), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idBiletu}")
    public ResponseEntity<String> deleteBilety(@PathVariable(value = "idBiletu") long idBiletu){
        biletyService.deleteBilety(idBiletu);
        return new ResponseEntity<>("Bilet został usunięty", HttpStatus.OK);
    }
}
