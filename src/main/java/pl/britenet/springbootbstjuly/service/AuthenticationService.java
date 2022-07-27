package pl.britenet.springbootbstjuly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.britenet.campus.object.User;
import pl.britenet.campus.service.UserService;
import pl.britenet.springbootbstjuly.model.UserCredentials;
import pl.britenet.springbootbstjuly.model.UserLoginData;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final Map<String, Integer> activeTokens;
    private final UserService userService;

    @Autowired
    public AuthenticationService(UserService userService) {
        this.activeTokens = new HashMap<>();
        this.userService = userService;
    }

    public UserLoginData login(UserCredentials userCredentials) {
        Optional<User> oUser = this.userService.getUser(userCredentials.getUsername(), userCredentials.getPassword());
        if (oUser.isPresent()) {
            int userId = oUser.get().getId();
            String userToken = UUID.randomUUID().toString();
            this.activeTokens.put(userToken, userId);
            return new UserLoginData(userId, userToken);
        }
        else {
            throw new IllegalStateException("Invalid credentials.");
        }
    }

    public boolean isLogged(String userToken) {
        return this.activeTokens.containsKey(userToken);
    }

    public int getUserId(String token) {
        return this.activeTokens.get(token);
    }

    public void register(UserCredentials userCredentials) {
        //TODO
    }
}
