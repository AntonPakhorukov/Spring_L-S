package Spring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class UserRepository {
    private Map<Long, User> users = new ConcurrentHashMap<>(); // лишена проблем с многопоточностью
    private AtomicLong counter = new AtomicLong(); // Atomic используем как потокобезопасный тип, одновременное обращение

    public List<User> findAll() {
        return new ArrayList<>(users.values()); // возвращаем новый список и наполняем его значениями = user'ами
    }

    public User findById(Long id) {
        return users.get(id);
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(counter.incrementAndGet()); // incrementAndGet() - метод берет счетчик, увеличивает на 1, возвращает данные
        }
        users.put(user.getId(), user);
        return user;
    }
}
