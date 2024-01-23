package com.park.park.controllers;

import com.park.park.dto.KlienciDTO;
import com.park.park.dto.UserDTO;
import com.park.park.entities.KlienciEntity;
import com.park.park.entities.Role;
import com.park.park.entities.UserEntity;
import com.park.park.repositories.KlienciRepository;
import com.park.park.repositories.RoleRepository;
import com.park.park.repositories.UserRepository;
import com.park.park.responses.AuthResponse;
import com.park.park.security.JWTTokenGenerator;
import com.park.park.services.KlienciService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static com.park.park.security.JWTAuthenticationFilter.getJWTFromRequest;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final KlienciService klienciService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final KlienciRepository klienciRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenGenerator jwtTokenGenerator;
    @Autowired
    public AuthController(KlienciService klienciService, AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, KlienciRepository klienciRepository, PasswordEncoder passwordEncoder, JWTTokenGenerator jwtTokenGenerator) {
        this.klienciService = klienciService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.klienciRepository = klienciRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            String jsonBad = "{ \"code\": \"404\", \"message\": \"This username is already taken!\" }";

            return new ResponseEntity<>(jsonBad, HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        if (roleRepository.findByName("USER").isEmpty()) {
            Role newRole = new Role();
            newRole.setName("USER");
            roleRepository.save(newRole);
        }

        Role roles = roleRepository.findByName("USER").get();
        userEntity.setRoles(Collections.singletonList(roles));
        userEntity.setRoleType("USER");

        KlienciDTO klienciDTO = new KlienciDTO();

        klienciDTO.setImie(userDTO.getImie());
        klienciDTO.setNazwisko(userDTO.getNazwisko());
        klienciDTO.setDataUrodzenia(userDTO.getDataUrodzenia());

        KlienciDTO finalKlienci = klienciService.createKlienci(klienciDTO);

        userEntity.setSystemId(finalKlienci.getIdKlienta());

        userRepository.save(userEntity);

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateToken(authentication);

        String json = "{ \"code\": \"200\", \"accessToken\": \"" + token + "\", \"role\": \"" + userEntity.getRoleType() + "\" }";

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateToken(authentication);
        UserEntity userEntity = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Nie ma użytkownika o danym usernamie"));

        return new ResponseEntity<>(new AuthResponse(token, userEntity.getRoleType()), HttpStatus.OK);
    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserDTO> getUserInfo(HttpServletRequest request){
        String token = getJWTFromRequest(request);
        String username = jwtTokenGenerator.getUsernameFromJWT(token);
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user with this id!"));

        KlienciEntity klienciEntity = klienciRepository.findById(userEntity.getSystemId())
                .orElseThrow(() -> new RuntimeException("No klient corelated wth this user"));

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(username);
        userDTO.setImie(klienciEntity.getImie());
        userDTO.setNazwisko(klienciEntity.getNazwisko());
        userDTO.setDataUrodzenia(klienciEntity.getDataUrodzenia().toString());
        userDTO.setRoleType(userEntity.getRoleType());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/makeadmin")
    public ResponseEntity<UserDTO> makeAdmin(String username){
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user with this id!"));

        if (roleRepository.findByName("ADMIN").isEmpty()) {
            Role newRole = new Role();
            newRole.setName("ADMIN");
            roleRepository.save(newRole);
        }

        Role roles = roleRepository.findByName("ADMIN").get();
        userEntity.setRoles(Collections.singletonList(roles));
        userEntity.setRoleType("ADMIN");
        UserEntity finalUser = userRepository.save(userEntity);

        KlienciEntity klienciEntity = klienciRepository.findById(finalUser.getSystemId())
                .orElseThrow(() -> new RuntimeException("No klient corelated wth this user"));

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(username);
        userDTO.setImie(klienciEntity.getImie());
        userDTO.setNazwisko(klienciEntity.getNazwisko());
        userDTO.setDataUrodzenia(klienciEntity.getDataUrodzenia().toString());
        userDTO.setRoleType(finalUser.getRoleType());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
