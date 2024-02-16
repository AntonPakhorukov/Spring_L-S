package SpringSecurity.dto;

/**
 * Record удобен тем, что он не изменяемый (нельзя изменить свойства после их создания)
 * и потокобезопасный
 * @param username - имя пользователя
 * @param password - пароль пользователя
 */
public record UserDTO (
        String username,
        String password) {

}
