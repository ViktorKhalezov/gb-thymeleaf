package ru.gb.gbthymeleaf.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.gb.gbapi.config.FeignConfig;
import ru.gb.gbapi.product.api.ProductGateway;
import ru.gb.gbapi.security.AuthenticationUserDto;
import ru.gb.gbapi.security.UserDto;
import ru.gb.gbapi.security.api.AuthGateway;
import ru.gb.gbapi.security.api.UserGateway;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserGateway userGateway;
    private final AuthGateway authGateway;

    public ResponseEntity<UserDto> register(UserDto user){
        return userGateway.handlePost(user);
    }

    public ResponseEntity<AuthenticationUserDto> login(AuthenticationUserDto user){
        return authGateway.loginUser(user);
    }
}