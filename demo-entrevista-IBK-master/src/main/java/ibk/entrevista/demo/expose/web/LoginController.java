package ibk.entrevista.demo.expose.web;

import ibk.entrevista.demo.model.login.AuthRequest;
import ibk.entrevista.demo.model.login.AuthResponse;
import ibk.entrevista.demo.security.JWTUtil;
import ibk.entrevista.demo.security.PBKDF2Encoder;
import ibk.entrevista.demo.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@AllArgsConstructor
public class LoginController {

    private JWTUtil jwtUtil;
    private UserService userService;
    private PBKDF2Encoder passwordEncoder;


    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return userService.findByUsername(ar.getUsername())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
