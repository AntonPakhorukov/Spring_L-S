package Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/api")
public class DataProcessingController {
    private final DataProcessingService dataProcessingService;
    @Autowired
    public DataProcessingController(DataProcessingService dataProcessingService){
        this.dataProcessingService = dataProcessingService;
    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> hello(){ // Метод параметризуем как строка
        String response = this.dataProcessingService.getGreeting(); // В строку записываем выполнение метода
        return new ResponseEntity<>(response, HttpStatus.OK); // возвращаем строку и статус ответа
    }
    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public ResponseEntity<List<User>> sortUsers(@RequestBody List<User> users){
        //RequestBody = взять данные из тела запроса
        return new ResponseEntity<>(this.dataProcessingService.sortUsersByAge(users), HttpStatus.OK);
        //Можно сократить код, сразу возвращая результат
    }
    @RequestMapping(value = "/filter/{age}", method = RequestMethod.GET)
    // {age} = указываем, что здесь будет переменная для метода
    public ResponseEntity<List<User>> filterByAge(@PathVariable("age") Integer age, @RequestBody List<User> users){
        //PathVariable = взять переменную из адресной строки запроса
        return new ResponseEntity<>(this.dataProcessingService.filterUsersByAge(users, age), HttpStatus.OK);
    }
    @RequestMapping(value = "/average", method = RequestMethod.POST)
    public ResponseEntity<Double> average(@RequestBody List<User> users){
        return new ResponseEntity<>(this.dataProcessingService.calculateAverageAge(users), HttpStatus.OK);
    }
}
