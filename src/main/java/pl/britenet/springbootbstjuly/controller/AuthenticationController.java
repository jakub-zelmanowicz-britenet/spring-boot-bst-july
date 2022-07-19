package pl.britenet.springbootbstjuly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.springbootbstjuly.model.UserCredentials;
import pl.britenet.springbootbstjuly.model.UserLoginData;
import pl.britenet.springbootbstjuly.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public UserLoginData login(@RequestBody UserCredentials userCredentials) {
        return this.authenticationService.login(userCredentials);
    }

    @GetMapping("/{userToken}")
    public boolean login(@PathVariable String userToken) {
        return this.authenticationService.isLogged(userToken);
    }
}
