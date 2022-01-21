package ibk.entrevista.demo.service.impl;

import ibk.entrevista.demo.model.entity.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * This is just an example, you can load the user from the database from the repository.
 * 
 */
@Service
public class UserService {

    private Map<String, User> data;

    @PostConstruct
    public void init() {
        data = new HashMap<>();

        //username:passwowrd -> user:user
        data.put("user", new User("user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY="));

        //username:passwowrd -> admin:admin
        data.put("admin", new User("admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w="));
    }

    public Mono<User> findByUsername(String username) {
        return Mono.justOrEmpty(data.get(username));
    }
}
