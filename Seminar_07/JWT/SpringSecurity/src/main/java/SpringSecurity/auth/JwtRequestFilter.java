package SpringSecurity.auth;

import SpringSecurity.service.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;

    @Autowired
    public JwtRequestFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // этот фильтр будет заниматься безопасностью, чтобы каждый запрос проходил через этот фильтр
        http.addFilterBefore(this, UsernamePasswordAuthenticationFilter.class);
        // при этом для /auth/login позволяем делать все
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login").permitAll()
                .anyRequest().authenticated());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /**
     * Фильтр переопределяет проверку токена для Spring
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = request.getHeader("Authorization"); // берем наш токен

            if (token != null && token.startsWith("Bearer ")) { // Если токен есть и заканчивается строка указанным суффиксом
                // Validate the JWT token
                //декодирование токена
                String tokenWithoutBearer = token.substring(7); // вытаскиваем токен без "Bearer "
                Authentication authentication = jwtTokenService.getAuthentication(tokenWithoutBearer); // получаем аутентификацию из токена
                SecurityContextHolder.getContext().setAuthentication(authentication); // проверка аутентификации
            }
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        filterChain.doFilter(request, response); // если фильтр прошел, то он пропустит запрос дальше по цепочке
    }
}
