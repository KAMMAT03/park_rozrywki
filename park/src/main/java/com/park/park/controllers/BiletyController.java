package com.park.park.controllers;

import com.park.park.dto.BiletyDTO;
import com.park.park.responses.DeleteResponse;
import com.park.park.responses.ModelResponse;
import com.park.park.services.BiletyService;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BiletyDTO> createBilety(HttpServletRequest request,
                                                  @RequestBody BiletyDTO biletyDTO){
        return new ResponseEntity<>(biletyService.createBilety(request, biletyDTO), HttpStatus.OK);
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

    @GetMapping("/getall/klient")
    public ResponseEntity<ModelResponse<BiletyDTO>> getAllBiletyByKlient(
            HttpServletRequest request,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(biletyService.getAllBiletyByKlient(request, pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/update/{idBiletu}")
    public ResponseEntity<BiletyDTO> updateBilety(HttpServletRequest request,
                                                  @PathVariable(value = "idBiletu") long idBiletu,
                                                  @RequestBody BiletyDTO biletyDTO){
        return new ResponseEntity<>(biletyService.updateBilety(request, biletyDTO, idBiletu), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idBiletu}")
    public ResponseEntity<DeleteResponse> deleteBilety(HttpServletRequest request,
                                               @PathVariable(value = "idBiletu") long idBiletu){
        biletyService.deleteBilety(request, idBiletu);
        return new ResponseEntity<>(new DeleteResponse("Bilet został usunięty", idBiletu), HttpStatus.OK);
    }
}
