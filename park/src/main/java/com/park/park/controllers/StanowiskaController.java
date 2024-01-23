package com.park.park.controllers;

import com.park.park.dto.StanowiskaDTO;
import com.park.park.responses.DeleteResponse;
import com.park.park.responses.ModelResponse;
import com.park.park.services.StanowiskaService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/stanowiska")
public class StanowiskaController {
    private final StanowiskaService stanowiskaService;

    public StanowiskaController(StanowiskaService stanowiskaService) {
        this.stanowiskaService = stanowiskaService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StanowiskaDTO> createStanowiska(@RequestBody StanowiskaDTO stanowiskaDTO){
        return new ResponseEntity<>(stanowiskaService.createStanowiska(stanowiskaDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{idStanowiska}")
    public ResponseEntity<StanowiskaDTO> getStanowiskaById(
            @PathVariable(value = "idStanowiska") long idStanowiska){
        return new ResponseEntity<>(stanowiskaService.getStanowiskaById(idStanowiska), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ModelResponse<StanowiskaDTO>> getAllStanowiska(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(stanowiskaService.getAllStanowiska(pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/update/{idStanowiska}")
    public ResponseEntity<StanowiskaDTO> updateStanowiska(@PathVariable(value = "idStanowiska") long idStanowiska,
                                                      @RequestBody StanowiskaDTO stanowiskaDTO){
        return new ResponseEntity<>(stanowiskaService.updateStanowiska(stanowiskaDTO, idStanowiska), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idStanowiska}")
    @Transactional
    public ResponseEntity<DeleteResponse> deleteStanowiska(@PathVariable(value = "idStanowiska") long idStanowiska){
        stanowiskaService.deleteStanowiska(idStanowiska);
        return new ResponseEntity<>(new DeleteResponse("Stanowisko zostało usunięte", idStanowiska), HttpStatus.OK);
    }
}
