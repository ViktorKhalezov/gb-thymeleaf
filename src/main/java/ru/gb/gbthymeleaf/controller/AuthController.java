package ru.gb.gbthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.gb.api.security.AuthenticationUserDto;
import ru.gb.gb.api.security.UserDto;
import ru.gb.api.security.api.AuthGateway;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthGateway authGateway;

    @GetMapping
    public String getLogin(Model model) {
        AuthenticationUserDto user = new AuthenticationUserDto();
        model.addAttribute("user", user);
        return "auth-form";
    }

    @PostMapping("/login")
    public String loginUser(AuthenticationUserDto authenticationUserDto, HttpServletResponse response){
            response.addCookie(new Cookie("jwt", authGateway.login(authenticationUserDto).getBody().get("token")));
            return "redirect:/product";
    }

}
