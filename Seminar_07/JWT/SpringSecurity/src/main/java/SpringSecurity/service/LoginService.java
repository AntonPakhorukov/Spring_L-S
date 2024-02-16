package SpringSecurity.service;

import SpringSecurity.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {
    /**
     * Создаем Map который имеет ключ/значение:
     * user=password и admin=admin
     */
    Map<String, String> userMap = new HashMap<>(Map.of("user", "password", "admin", "admin"));

    /**
     * Создаем метод проверки
     * @param userDTO - принимаем на вход пользователя
     * создаем строку password и записываем в нее пароль по связке ключ в Map = имя пользователя
     * @return - если значение совпало, есть пароль, и пароль соответствует паролю пользователя,
     * то возвращаем 1, в обратном случае null.
     */
    public Optional<Integer> login(UserDTO userDTO) {
        String password = userMap.get(userDTO.username());
        if (password != null && password.equals(userDTO.password())) {
            return Optional.of(1);
        }
        return Optional.empty();
    }
}
