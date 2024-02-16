package SpringSecurity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * securedEnable - означает, что мы включаем для своего кода аннотацию secure и можем там прописывать кому,
 * с какими ролями можно иметь доступ к какому компоненту.
 * jsr250Enabled - обеспечение ролей, означает, что включена Role Based Security - предполагает использование ролей
 */
@Configuration
@EnableWebSecurity // включаем конфигурацию web security
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true) // определяем методы обеспечения security
public class SecurityConfig {

    /**
     * Пример
     * */
    @Value("${spring.security.debug:false}")
    boolean securityDebug;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable) // Отключили csrf
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> // вызываем авторизацию всех http запросов:
                        authorizationManagerRequestMatcherRegistry // передаем менеджера сопоставления запросов с регистром,
                                .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN") // выбираем метод и к нему возможную роль
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN") // указываем для страницы админа и все последующие страницы,
                                // то есть, доступ к нашему endpoint admin имеет только администратор
                                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // так же делаем доступ для юзера
                                .requestMatchers("/login/**").permitAll() // на эту страницу могу заходить все
                                .anyRequest() // все последующие запросы
                                .authenticated()) // идут по аутентификации
                .httpBasic(Customizer.withDefaults()) // описывает базовую конфигурацию для доступа
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        // отключаем доступ к:
        return web -> web.debug(securityDebug)
                .ignoring()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }

}
