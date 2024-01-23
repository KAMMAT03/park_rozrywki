package com.park.park.controllers;

import com.park.park.dto.RodzajeMiejscaParkingowegoDTO;
import com.park.park.responses.DeleteResponse;
import com.park.park.responses.ModelResponse;
import com.park.park.services.RodzajeMiejscaParkingowegoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/parking/rodzaje")
public class RodzajeMiejscaParkingowegoController {
    private final RodzajeMiejscaParkingowegoService rodzajeMiejscaParkingowegoService;
    @Autowired
    public RodzajeMiejscaParkingowegoController(RodzajeMiejscaParkingowegoService rodzajeMiejscaParkingowegoService) {
        this.rodzajeMiejscaParkingowegoService = rodzajeMiejscaParkingowegoService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RodzajeMiejscaParkingowegoDTO> createRodzajeMiejscaParkingowego(
            @RequestBody RodzajeMiejscaParkingowegoDTO rodzajeMiejscaParkingowegoDTO){
        return new ResponseEntity<>(rodzajeMiejscaParkingowegoService
                .createRodzajeMiejscaParkingowego(rodzajeMiejscaParkingowegoDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{idRodzajuMiejscaParkingowego}")
    public ResponseEntity<RodzajeMiejscaParkingowegoDTO> getRodzajeMiejscaParkingowego(
            @PathVariable(value = "idRodzajuMiejscaParkingowego") long idRodzajuMiejscaParkingowego){
        return new ResponseEntity<>(rodzajeMiejscaParkingowegoService
                .getRodzajeMiejscaParkingowegoById(idRodzajuMiejscaParkingowego), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ModelResponse<RodzajeMiejscaParkingowegoDTO>> getAllRodzajeMiejscaParkingowego(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize){
        return new ResponseEntity<>(rodzajeMiejscaParkingowegoService
                .getAllRodzajeMiejscaParkingowego(pageNo, pageSize), HttpStatus.OK);
    }

    @PutMapping("/update/{idRodzajuMiejscaParkingowego}")
    public ResponseEntity<RodzajeMiejscaParkingowegoDTO> updateRodzajeMiejscaParkingowego(
            @PathVariable(value = "idRodzajuMiejscaParkingowego") long idRodzajuMiejscaParkingowego,
            @RequestBody RodzajeMiejscaParkingowegoDTO rodzajeMiejscaParkingowegoDTO){
        return new ResponseEntity<>(rodzajeMiejscaParkingowegoService.updateRodzajeMiejscaParkingowego
                (rodzajeMiejscaParkingowegoDTO, idRodzajuMiejscaParkingowego), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idRodzajuMiejscaParkingowego}")
    @Transactional
    public ResponseEntity<DeleteResponse> deleteRodzajeMiejscaParkingowego(
            @PathVariable(value = "idRodzajuMiejscaParkingowego") long idRodzajuMiejscaParkingowego){
        rodzajeMiejscaParkingowegoService.deleteRodzajeMiejscaParkingowego(idRodzajuMiejscaParkingowego);
        return new ResponseEntity<>(new DeleteResponse("Rodzaj miejsca parkingowego został usunięty", idRodzajuMiejscaParkingowego), HttpStatus.OK);
    }
}
