package com.park.park.controllers;

import com.park.park.dto.TypyBiletowDTO;
import com.park.park.responses.ModelResponse;
import com.park.park.services.TypyBiletowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/typybiletow")
public class TypyBiletowController {
    private final TypyBiletowService typyBiletowService;
    @Autowired
    public TypyBiletowController(TypyBiletowService typyBiletowService) {
        this.typyBiletowService = typyBiletowService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TypyBiletowDTO> createTypyBiletow(@RequestBody TypyBiletowDTO typyBiletowDTO){
        return new ResponseEntity<>(typyBiletowService.createTypyBiletow(typyBiletowDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{idTypuBiletu}")
    public ResponseEntity<TypyBiletowDTO> getTypyBiletowById(
            @PathVariable(value = "idTypuBiletu") long idTypuBiletu){
        return new ResponseEntity<>(typyBiletowService.getTypyBiletowById(idTypuBiletu), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ModelResponse<TypyBiletowDTO>> getAllTypyBiletow(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(typyBiletowService.getAllTypyBiletow(pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/update/{idTypuBiletu}")
    public ResponseEntity<TypyBiletowDTO> updateTypyBiletow(@PathVariable(value = "idTypuBiletu") long idTypuBiletu,
                                                      @RequestBody TypyBiletowDTO typyBiletowDTO){
        return new ResponseEntity<>(typyBiletowService.updateTypyBiletow(typyBiletowDTO, idTypuBiletu), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idTypuBiletu}")
    public ResponseEntity<String> deleteTypyBiletow(@PathVariable(value = "idTypuBiletu") long idTypuBiletu){
        typyBiletowService.deleteTypyBiletow(idTypuBiletu);
        return new ResponseEntity<>("Typ biletu został usunięty", HttpStatus.OK);
    }
}
