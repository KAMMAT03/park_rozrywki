package com.park.park.controllers;

import com.park.park.dto.KlienciDTO;
import com.park.park.entities.UserEntity;
import com.park.park.repositories.UserRepository;
import com.park.park.responses.DeleteResponse;
import com.park.park.responses.ModelResponse;
import com.park.park.security.JWTTokenGenerator;
import com.park.park.services.KlienciService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.park.park.security.JWTAuthenticationFilter.getJWTFromRequest;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/klienci")
public class KlienciController {
    private final KlienciService klienciService;
    private final UserRepository userRepository;
    private final JWTTokenGenerator jwtTokenGenerator;
    @Autowired
    public KlienciController(KlienciService klienciService, UserRepository userRepository, JWTTokenGenerator jwtTokenGenerator) {
        this.klienciService = klienciService;
        this.userRepository = userRepository;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<KlienciDTO> createKlienci(@RequestBody KlienciDTO klienciDTO){
        return new ResponseEntity<>(klienciService.createKlienci(klienciDTO), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<KlienciDTO> getKlienciById(
            HttpServletRequest request){

        String token = getJWTFromRequest(request);
        String username = jwtTokenGenerator.getUsernameFromJWT(token);
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user with this id!"));
        long idKlienta = userEntity.getSystemId();

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
    @Transactional
    public ResponseEntity<DeleteResponse> deleteKlienci(@PathVariable(value = "idKlienta") long idKlienta){
        klienciService.deleteKlienci(idKlienta);
        return new ResponseEntity<>(new DeleteResponse("Klient został usunięty", idKlienta), HttpStatus.OK);
    }
}
