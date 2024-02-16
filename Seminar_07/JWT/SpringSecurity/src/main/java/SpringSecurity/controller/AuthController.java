package SpringSecurity.controller;

import SpringSecurity.dto.UserDTO;
import SpringSecurity.service.JwtTokenService;
import SpringSecurity.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtTokenService jwtTokenService; // работой с токеном и аутентификацией будет заниматься jwtTokenService
    private final LoginService loginService; // логином будет заниматься loginService

    public AuthController(JwtTokenService jwtTokenService, LoginService loginService) {
        this.jwtTokenService = jwtTokenService;
        this.loginService = loginService;
    }

    /**
     * Метод аутентификации
     * @param userDTO - принимает пользователя
     * @return - возвращает его логин и пароль
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
        return loginService.login(userDTO) // Проверяем, что UserDTO существует в нашей базе
                .map(userId -> { // если существует, то нужно сделать map нашего userId и сгенерировать токен,
                    String token = jwtTokenService.generateToken(userId, "USER"); // который будет генерироваться нашим токен сервисом
                    return new ResponseEntity<>("Bearer " + token, HttpStatus.OK); // вернуть сгенерированный токен со статусом ОК
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED)); // либо вернуть ошибку, если пользователя не существует
    }
}
