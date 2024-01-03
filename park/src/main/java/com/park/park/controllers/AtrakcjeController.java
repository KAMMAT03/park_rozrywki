package com.park.park.controllers;

import com.park.park.dto.AtrakcjeDTO;
import com.park.park.responses.ModelResponse;
import com.park.park.services.AtrakcjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/atrakcje")
public class AtrakcjeController {
    AtrakcjeService atrakcjeService;
    @Autowired
    public AtrakcjeController(AtrakcjeService atrakcjeService) {
        this.atrakcjeService = atrakcjeService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AtrakcjeDTO> createAtrakcje(@RequestBody AtrakcjeDTO atrakcjeDTO){
        return new ResponseEntity<>(atrakcjeService.createAtrakcje(atrakcjeDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{idAtrakcji}")
    public ResponseEntity<AtrakcjeDTO> getAtrakcjeById(
            @PathVariable(value = "idAtrakcji") long idAtrakcji){
        return new ResponseEntity<>(atrakcjeService.getAtrakcjeById(idAtrakcji), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ModelResponse<AtrakcjeDTO>> getAllAtrakcje(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(atrakcjeService.getAllAtrakcje(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/getonly")
    public ResponseEntity<ModelResponse<AtrakcjeDTO>> getOnlyAtrakcje(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(atrakcjeService.getOnlyAtrakcje(pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/update/{idAtrakcji}")
    public ResponseEntity<AtrakcjeDTO> updateAtrakcje(@PathVariable(value = "idAtrakcji") long idAtrakcji,
                                                      @RequestBody AtrakcjeDTO atrakcjeDTO){
            return new ResponseEntity<>(atrakcjeService.updateAtrakcje(atrakcjeDTO, idAtrakcji), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idAtrakcji}")
    public ResponseEntity<String> deleteAtrakcje(@PathVariable(value = "idAtrakcji") long idAtrakcji){
        atrakcjeService.deleteAtrakcje(idAtrakcji);
        return new ResponseEntity<>("Atrakcja została usunięta", HttpStatus.OK);
    }
}
