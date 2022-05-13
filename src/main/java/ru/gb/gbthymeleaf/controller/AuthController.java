package ru.gb.gbthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.gbapi.security.AuthenticationUserDto;
import ru.gb.gbapi.security.UserDto;
import ru.gb.gbshopmart.service.UserService; // исправить

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @GetMapping
    public String getLogin(Model model) {
        AuthenticationUserDto user = new AuthenticationUserDto();
        model.addAttribute("user", user);
        return "auth-form";
    }

    @PostMapping("/login")
    public String loginUser(AuthenticationUserDto authenticationUserDto){
        ResponseEntity<AuthenticationUserDto> response = userService.login(authenticationUserDto);
        if (response.getStatusCode() == HttpStatus.OK)
            return "redirect:/product";
        else
            return "auth-form";
    }
}