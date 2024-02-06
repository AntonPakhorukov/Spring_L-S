package SpringThymeleafSem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class RandomController {
    @GetMapping("/random")
    public String getRandomNumber(Model model){
        Random random = new Random();
        model.addAttribute("number", random.nextInt(0, 101));
        // number = название переменной в html странице, на место которой подставляем рандомное число
        return "random"; // указываем название html файла, так как он находится в templates, Model это сама понимает
    }
}
