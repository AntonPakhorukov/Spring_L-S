package Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        // Добавляем атрибут users -> разрешаем tymeleaf'у использовать переменную users
        // а в качестве значения переменной users подставим список user'ов
        return "users";
    }
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model){
        // Когда мы передаем в метод Model model - говорим Spring'у, что сюда надо вставить класс Model,
        // в который мы положим определенные значения
        model.addAttribute("user", userService.getUserById(id));
        return "userProfile";
    }
}
