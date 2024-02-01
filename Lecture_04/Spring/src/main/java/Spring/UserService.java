package Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository; // если ошибка - добавить @Component в класс UserRepository
    public List<User> getAllUsers(){
        userRepository.save(new User(null, "Garry", "garry@hogwards.mgc"));
        userRepository.save(new User(null, "Germiona", "germiona@hogwards.mgc"));
        userRepository.save(new User(null, "Ron", "ron@hogwards.mgc"));
        return userRepository.findAll();
    }
    public User getUserById(Long id){
        return userRepository.findById(id);
    }
    public User addUser(User user){
        return userRepository.save(user);
    }
}
