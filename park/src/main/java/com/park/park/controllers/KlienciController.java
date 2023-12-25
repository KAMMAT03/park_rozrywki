package com.park.park.controllers;

import com.park.park.dto.KlienciDTO;
import com.park.park.responses.ModelResponse;
import com.park.park.services.KlienciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/klienci")
public class KlienciController {
    private final KlienciService klienciService;
    @Autowired
    public KlienciController(KlienciService klienciService) {
        this.klienciService = klienciService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<KlienciDTO> createKlienci(@RequestBody KlienciDTO klienciDTO){
        return new ResponseEntity<>(klienciService.createKlienci(klienciDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{idKlienta}")
    public ResponseEntity<KlienciDTO> getKlienciById(
            @PathVariable(value = "idKlienta") long idKlienta){
        return new ResponseEntity<>(klienciService.getKlienciById(idKlienta), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ModelResponse<KlienciDTO>> getAllKlienci(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(klienciService.getAllKlienci(pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/update/{idKlienta}")
    public ResponseEntity<KlienciDTO> updateKlienci(@PathVariable(value = "idKlienta") long idKlienta,
                                                      @RequestBody KlienciDTO klienciDTO){
        return new ResponseEntity<>(klienciService.updateKlienci(klienciDTO, idKlienta), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idKlienta}")
    public ResponseEntity<String> deleteKlienci(@PathVariable(value = "idKlienta") long idKlienta){
        klienciService.deleteKlienci(idKlienta);
        return new ResponseEntity<>("Klient został usunięty", HttpStatus.OK);
    }
}
