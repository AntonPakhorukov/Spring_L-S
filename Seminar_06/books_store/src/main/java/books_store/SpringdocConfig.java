package books_store;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // объявляем конфигурацию
public class SpringdocConfig {
    @Bean // первый бин о чем наш api
    public OpenAPI apiInfo(){
        return new OpenAPI()
                .info(new Info().title("My api") // описываем наш api
                        .version("1.0.0")); // описываем нашу версию
    }
    @Bean // второй бин - собираем api
    public GroupedOpenApi httpApi(){
        return GroupedOpenApi.builder() // собираем:
                .group("http") // указываем группу
                .pathsToMatch("/**") // указываем какие-то пути
                .build(); // собираем
    }
}

