package com.example.server.controller;

import com.example.server.controller.dto.LoginRequestDTO;
import com.example.server.controller.dto.LoginResponseDTO;
import com.example.server.controller.dto.UserDTO;
import com.example.server.controller.dto.UserSignInRequestDTO;
import com.example.server.controller.dto.UserSignInResultDTO;
import com.example.server.models.User;
import com.example.server.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtEncoder jwtEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @PostMapping
    public ResponseEntity<UserSignInResultDTO> signIn(@RequestBody UserSignInRequestDTO userSignInRequest) {

        final var userSignInResult = userService.signIn(userSignInRequest.user());

        return (userSignInResult.success() ? ResponseEntity.ok() : ResponseEntity.status(HttpStatus.CONFLICT))
                .body(userSignInResult);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        try {
            final var authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            final var user = (User) authentication.getPrincipal();

            var now = Instant.now();
            var expiry = 3600L;

            var scope =
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(" "));

            var claims =
                    JwtClaimsSet.builder()
                            .issuer("blog-server")
                            .issuedAt(now)
                            .expiresAt(now.plusSeconds(expiry))
                            .subject(user.getCuid().toString())
                            .claim("roles", scope)
                            .build();

            var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(LoginResponseDTO.of(new UserDTO(user.getCuid(), user.getName()), token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
