package SpringThymeleafSem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Отличие @Controller от @RestController заключается в том, что в нашем текущем решение
 * мы отображаем страницу html, а если мы указали бы @RestController, то мы увидели бы не
 * страницу html, а строчку "about.html", так как @RestController не умеет работать со страницами,
 * а возвращает значение, то есть вернет строку.
 *
 * При использовании Thymeleaf html страницы about и index перестанут работать, так как Thymeleaf
 * перехватывает все html страницы.
 * Чтобы это исправить, нужно сказать, просто переходить на страницу => return "forward:/index.html"
 */
@Controller
public class ControllerAbout {
    @GetMapping("/about")
    public String about(){
        return "forward:/about.html";
    }
    @RequestMapping(value = "/index")
    public String index(){
        return "index.html";
    }

}
