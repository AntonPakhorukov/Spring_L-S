package SpringSecurity.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenService {
    /**
     * Данные взяты из файла application.properties
     */
    @Value("${jwt.token.validity}")
    private long JWT_TOKEN_VALIDITY;
    @Value("${jwt.secret}")
    private String SECRET;

    /**
     * Генерируем наш токен
     *
     * @return сгенерированные токен
     */
    public String generateToken(int userId, String role) {
        Map<String, Object> claims = new HashMap<>(); // то, куда мы все параметры токена положим
        claims.put("role", role); // кладем роли???
        return Jwts.builder() // собираем токен
                .setClaims(claims) // положили наш map с ролями
                .setSubject(String.valueOf(userId)) // тот, кто будет закодирован - User
                .setIssuedAt(new Date(System.currentTimeMillis())) // когда токен создан
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) // когда токен истекает
                .signWith(SignatureAlgorithm.HS512, SECRET) // определяем каким алгоритмом будем шифровать
                .compact(); // упаковываем
    }

    /**
     * Метод получения аутентификации из токена
     * должны вытащить claims и получить роль
     */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody(); // получили claims
        // когда получили claims - получили все параметры токена
        int userId = Integer.parseInt(claims.getSubject()); // получили userId
        String role = claims.get("role", String.class); // получили роль
        Collection<GrantedAuthority> authorities = new ArrayList<>(); // выбираем authorities и добавляем разные роли
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }

    public int getUserIdFromToken(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization"); // вытаскиваем токен из header "Authorization"
        String tokenWithoutBearer = token.substring(7); // обрезаем Bearer, то есть берем токен с 7-го символа
        return Integer.parseInt(getClaimFromToken(tokenWithoutBearer, Claims::getSubject));

    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }
}
