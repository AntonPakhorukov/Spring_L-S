package SpringSecurity;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @GetMapping("/login")
    public String loginEndpoint() {
        return "Login!";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "User!";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Admin!";
    }

    @GetMapping("/all")
    public String allRolesEndpoint() {
        return "All!";
    }

    @GetMapping("/delete")
    public String deleteEndpoint(@RequestBody String s) {
        return "Delete!" + s;
    }

}
